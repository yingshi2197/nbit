package com.noboll.business.interview.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.deliver.entity.Deliver;
import com.noboll.business.deliver.service.DeliverService;
import com.noboll.business.dict.constant.DictConstant;
import com.noboll.business.dict.entity.Dict;
import com.noboll.business.dict.service.DictService;
import com.noboll.business.interview.dao.InterviewDao;
import com.noboll.business.interview.entity.Interview;
import com.noboll.business.interview.service.InterviewService;
import com.noboll.business.resume.entity.Resume;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.StringUtil;

@Service("interviewService")
public class InterviewServiceImpl extends BaseServiceImpl<Interview>
		implements InterviewService {

	@Resource
	private InterviewDao interviewDao;
	@Resource
	private ResumeService resumeService;
	@Resource
	private DictService dictService;
	@Resource
	private DeliverService deliverService;
	
	@Override
	public BaseDao<Interview> getBaseDao() {
		return interviewDao;
	}

	@Override
	public void saveInterview(Interview interview) {
		// 添加面试信息
		this.saveEntity(interview);
	}

	@Override
	public void updateInterview(Interview interview) {
		this.updateEntity(interview);
	}
	
	/**
	 * 通过投递记录id得到电话面试记录
	 */
	public Interview getDhByDeliverId(String deliverId){
		return interviewDao.getByDeliverIdAndType(deliverId,DictConstant.DICT_CODE_DHMS);
	}
	/**
	 * 新增\修改电话面试记录
	 */
	public void saveUpdateDhInterview(Interview interview, Resume resume){
		// 查找投递记录
		if (StringUtil.isEmpty(interview.getDeliverId()))
			throw new BusinessException("无投递记录！");
		Deliver deliver = deliverService.getEntity(interview.getDeliverId());
		if (null == deliver || StringUtil.isEmpty(deliver.getResumeId())) 
			throw new BusinessException("无投递记录！");
		// 更新简历信息
		Resume resumeData = resumeService.getEntity(deliver.getResumeId());
		resumeData.setSchool(resume.getSchool());
		resumeData.setFinishTime(resume.getFinishTime());
		resumeData.setYears(resume.getYears());
		resumeData.setBirthday(resume.getBirthday());
		resumeData.setDegree(resume.getDegree());
		resumeData.setPay(resume.getPay());
		resumeService.updateEntity(resumeData);
		
		Dict dict = dictService.getByCode(DictConstant.DICT_CODE_DHMS);// 电话面试
		if (StringUtil.isEmpty(interview.getId())) {
			interview.setType(dict.getId());
			interview.setUserId(resume.getUserId());
			interview.setCustomerId(deliver.getCustomerId());
			this.saveEntity(interview);
		}else{
			this.updateEntity(interview);
		}
		
		// 修改投递状态，以及投递面试状态
		Dict dictResult = dictService.getEntity(interview.getResult());
		String deliverStatus = this.getDeliverStatusByResult(dictResult);
		deliver.setStatus(deliverStatus);
		deliver.setInterviewStatus(dictResult.getId());
		deliverService.updateStatus(deliver);
	}
	
	/**
	 * 通过面试结果得到投递状态<br>
	 * <li>电话面试通过：处理中</li>
	 * <li>电话面试未接听：回到待确认状态</li>
	 * <li>电话面试/现场面试不通过：投递失败</li>
	 * <li>现场面试通过：面试通过</li>
	 */
	private String getDeliverStatusByResult(Dict dictResult){
		String code = dictResult.getCode();
		List<String> success = new ArrayList<String>();
		success.add("xcmstg");
		success.add("dhmstg");
		Dict dictDeliverStatus = null;
		if (DictConstant.DICT_CODE_DHMSTG.equals(code)) {
			// 电话面试通过：处理中
			dictDeliverStatus = dictService.getByCode(DictConstant.DICT_CODE_DELIVER_INHAND);
		}else if(DictConstant.DICT_CODE_DHMSWJT.equals(code)){
			// 电话面试未接听：待确认
			dictDeliverStatus = dictService.getByCode(DictConstant.DICT_CODE_DELIVER_DAIQUEREN);
		}else if(DictConstant.DICT_CODE_DHMSBTG.equals(code) || DictConstant.DICT_CODE_XCMSBTG.equals(code)){
			// 电话面试不通过/现场面试不通过：投递失败
			dictDeliverStatus = dictService.getByCode(DictConstant.DICT_CODE_DELIVER_FAIL);
		}else if(DictConstant.DICT_CODE_XCMSTG.equals(code)){
			// 现场面试通过：面试通过
			dictDeliverStatus = dictService.getByCode(DictConstant.DICT_CODED_DELIVER_INTERVIEWTG);
		}
		return dictDeliverStatus.getId();
	}
	
	/**
	 * 通过投递记录id得到现场面试记录
	 */
	public Interview getXcByDeliverId(String deliverId){
		return interviewDao.getByDeliverIdAndType(deliverId,DictConstant.DICT_CODE_XCMS);
	}
	
	/**
	 * 新增\修改电话现场记录
	 */
	public void saveUpdateXcInterview(Interview interview){
		// 查找投递记录
		if (StringUtil.isEmpty(interview.getDeliverId()))
			throw new BusinessException("无投递记录！");
		Deliver deliver = deliverService.getEntity(interview.getDeliverId());
		if (null == deliver || StringUtil.isEmpty(deliver.getResumeId())) 
			throw new BusinessException("无投递记录！");
		// 更新简历信息
		Resume resumeData = resumeService.getEntity(deliver.getResumeId());
				
		Dict dict = dictService.getByCode(DictConstant.DICT_CODE_XCMS);// 电话面试
		if (StringUtil.isEmpty(interview.getId())) {
			interview.setType(dict.getId());
			interview.setUserId(resumeData.getUserId());
			interview.setCustomerId(deliver.getCustomerId());
			this.saveEntity(interview);
		}else{
			this.updateEntity(interview);
		}
		
		// 修改投递状态，以及投递面试状态
		Dict dictResult = dictService.getEntity(interview.getResult());
		String deliverStatus = this.getDeliverStatusByResult(dictResult);
		deliver.setStatus(deliverStatus);
		deliver.setInterviewStatus(dictResult.getId());
		deliverService.updateStatus(deliver);
	}
}
