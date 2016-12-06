package com.noboll.demo.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.cache.service.CacheService;
import com.noboll.demo.entity.Demo;
import com.noboll.demo.service.DemoService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller
@RequestMapping("/demo")
public class DemoController {
	@Resource
	private DemoService demoService;
	@Resource
	private CacheService cacheService;
	
	@RequestMapping("/toAdd")
	public String toAddDemo() {
		Page<Demo> page=demoService.getPageList("com.noboll.demo.dao.DemoDao.getAllEntity", new QueryParam(), new Page<Demo>(1, 10));
		cacheService.put("user", page);
//		System.out.println(BaseContext.getWebPath());
//		System.out.println(PropertiesUtil.getSettingValue("sys.cache.impl"));
//		UploadUtil.uploadFile(new File("D:\\转正审批流设计.docx"), "test", PathType.YMD);
		return "demo/addDemo";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object addDemo(@ModelAttribute("Demo") Demo demo) {
		Page page=(Page) cacheService.get("user");
		demoService.saveEntity(demo);
		return "操作成功";
	}
	
	
	public static void main(String[]args) throws Exception{
        
        //1.jpg是你的 主图片的路径
        InputStream is = new FileInputStream("d:/1.jpg");
        
        
        //通过JPEG图象流创建JPEG数据流解码器
        JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
        //解码当前JPEG数据流，返回BufferedImage对象
        BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
        //得到画笔对象
        Graphics g = buffImg.getGraphics();
        
       /* //创建你要附加的图象。
        //2.jpg是你的小图片的路径
        ImageIcon imgIcon = new ImageIcon("d:/1.jpg"); 
        
        //得到Image对象。
        Image img = imgIcon.getImage();
        
        //将小图片绘到大图片上。
        //5,300 .表示你的小图片在大图片上的位置。
        g.drawImage(img,5,330,null);*/
        
        //设置颜色。
        g.setColor(Color.BLACK);
        //最后一个参数用来设置字体的大小
        Font f = new Font("宋体",Font.BOLD,30);
        g.setFont(f);
        //10,20 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
        g.drawString("王应时",10,30);
        g.dispose();
        OutputStream os = new FileOutputStream("d:/union.jpg");
        
        //创键编码器，用于编码内存中的图象数据。
        
        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
        en.encode(buffImg);
        
        
        is.close();
        os.close();
        
        System.out.println ("合成结束。。。。。。。。");
        
        
    }    
}
