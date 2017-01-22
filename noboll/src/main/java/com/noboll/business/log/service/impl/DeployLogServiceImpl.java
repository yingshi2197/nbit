package com.noboll.business.log.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.noboll.business.log.dao.DeployLogDao;
import com.noboll.business.log.entity.DeployLog;
import com.noboll.business.log.service.DeployLogService;
import com.noboll.business.user.entity.User;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.util.PropertiesUtil;
import com.noboll.core.util.StringUtil;
import com.noboll.plugin.file.entity.PathType;
import com.noboll.plugin.file.util.UploadUtil;

@Service("deployLogService")
public class DeployLogServiceImpl extends BaseServiceImpl<DeployLog> implements DeployLogService {

	@Resource
	private DeployLogDao deployLogDao;
	
	@Override
	public BaseDao<DeployLog> getBaseDao() {
		return deployLogDao;
	}
	
	public List<DeployLog> queryDeployLogs(Date date,String status) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("startTime", date);
		map.put("status", status);
		return deployLogDao.getAllEntity(map);
	}
	
	public synchronized void deploy(final DeployLog entity,String type) {
		Date date=entity.getStartTime();
		
		final User user=(User) SystemContext.getLoginUser();
		if(null==date||date.getTime()<=new Date().getTime()) {
			
			// 如果是定时部署 ，需要修改状态
			if("timer".equals(type)){
				entity.setStatus("2");//表明部署中
				getBaseDao().updateEntity(entity);
			}
			
			
			new Thread(new Runnable() {
				public void run() {
					SystemContext.setLoginUser(user);
					//sh deploy.sh svn://192.168.1.32/招聘管理项目/源码/branch/3.2/msb 192.168.1.88 msb /data/msb
					String command="";
					String path=StringUtil.isEmpty(entity.getPath()) ? "0" : entity.getPath();
					if(StringUtil.isEmpty(entity.getIncrease())) {
						command=PropertiesUtil.getSettingValue("sys.deploy.all.command");
						command=command.replace("{0}"," "+entity.getSource()+" "+entity.getTarget()+" "+entity.getCode()+" "+path+" "+entity.getContainer());
					}else {
						command=PropertiesUtil.getSettingValue("sys.deploy.increase.command");
						command=command.replace("{0}"," "+entity.getIncrease()+" "+entity.getTarget()+" "+entity.getCode()+" "+path+" "+entity.getStart()+" "+entity.getContainer());
					}
					System.out.println(command);
			        try {  
			        	String s = null;
			            Process pro = Runtime.getRuntime().exec(command); 
			            BufferedReader stdInput = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			            BufferedReader stdError = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
			            while ((s = stdInput.readLine()) != null) {
			                System.out.println(s);
			            }
			            while ((s = stdError.readLine()) != null) {
			            	System.out.println(s);
			            }
			            int status=pro.waitFor();  
			            if(status==0) {
			            	entity.setStatus("1");
							entity.setReason("");
			            }else {
			            	entity.setStatus("0");
							entity.setReason("执行shell失败");
			            }
			        } catch (Exception e) {  
			        	e.printStackTrace();
			        	entity.setStatus("0");
						entity.setReason(e.getMessage());
			        }  
			        entity.setDeployTime(new Date());
					deployLogDao.deploy(entity);
				}
			}).start();
		}
	}
	
	public void saveEntity(final DeployLog entity,MultipartFile file) {
		
		if(null!=file&&file.getSize()!=0) {
			String prefix=PropertiesUtil.getSettingValue("sys.file.upload.path.absolute");
			String path=UploadUtil.uploadFile(file, "increase", PathType.YMD);
			entity.setIncrease((StringUtil.isEmpty(prefix) ? "" : prefix)+path);
		}
		Date date=entity.getStartTime();
		
		boolean deploy=false;
		if(null==date||date.getTime()<=new Date().getTime()) {
			entity.setStatus("2");//表明部署中
			deploy=true;
		}else {
			entity.setStatus("5");//表明未部署
		}
		getBaseDao().saveEntity(entity);
		if(deploy)
			deploy(entity,"");
	}

	
}
