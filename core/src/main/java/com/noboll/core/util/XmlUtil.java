package com.noboll.core.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.exception.BusinessException;



/**
 * 
* @ClassName: XmlUtil
* @Description: xml解析工具
* @author kent.wang@noboll.com.cn
* @date 2015年6月3日 上午11:44:54
*
 */
public class XmlUtil {
	
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	/**
	 * 
	* @Title: xmlToList 
	* @Description: 将xml文件转成list集合
	* @param @param cls  对象类
	* @param @param fileName  xml文件名，不要全路径，只需要名字
	* @param @param tagName  xml中对象对应的标签名
	* @param @return    设定文件 
	* @return List    返回类型 
	* @throws
	 */
	public static List xmlToList(Class cls,String fileName,String tagName) {  
		InputStream inputStream=null;
		try {
			//System.out.println(resourceLoader.getResource("/").getURL().getPath());
			inputStream = resourceLoader.getResource(fileName).getInputStream();
			List<Object> list = new ArrayList<Object>();  
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	        DocumentBuilder builder = factory.newDocumentBuilder();  
	        Document document = builder.parse(inputStream);  
	        Element element = document.getDocumentElement();  
	        List<String> props=ReflectUtil.getFields(cls);
	        NodeList bookNodes = element.getElementsByTagName(tagName);  
	        for(int i=0;i<bookNodes.getLength();i++){  
	            Element bookElement = (Element) bookNodes.item(i);  
	            Object obj=cls.newInstance();
	            if(bookElement.getAttributes().getLength()>0) {
	            	for(int j=0;j<bookElement.getAttributes().getLength();j++) {
	            		Node node=bookElement.getAttributes().item(j);
	            		if(props.contains(node.getNodeName())&&!StringUtil.isEmpty(node.getTextContent())) {
	            			ReflectUtil.setValue(obj, node.getNodeName(), node.getTextContent().replaceAll("[\n\r\t\\s]", ""));
	            		}
	            	}
	            	
	            }
	            
	            NodeList childNodes = bookElement.getChildNodes();  
//	          System.out.println("*****"+childNodes.getLength());  
	            for(int j=0;j<childNodes.getLength();j++){  
	            	Node node=childNodes.item(j);
	                if(node.getNodeType()==Node.ELEMENT_NODE){  
	                	if(props.contains(node.getNodeName())&&!StringUtil.isEmpty(node.getTextContent())) {
	                		ReflectUtil.setValue(obj, node.getNodeName(), node.getTextContent().replaceAll("[\n\r\t\\s]", ""));
	            		}
	                }  
	            }//end for j  
	            list.add(obj);  
	        }//end for i  
	        inputStream.close();
	        return list;  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if(null!=inputStream)
					inputStream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new BusinessException("解析异常");
		}
    }  
	
	/**
	 * 注意，对应的实体类上需要加上注解@XmlRootElement
	 */
	public static <T extends BaseEntity> T xmlToEntity(String str,Class<T> cls) {
		try {  
			JAXBContext context = JAXBContext.newInstance(cls);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            T obj = (T)unmarshaller.unmarshal(new StringReader(str));  
            return obj;
        } catch (JAXBException e) {  
        	e.printStackTrace();
            throw new BusinessException("xml转对象失败");
        }  
	}
	
	// 将xml文件读取成字符串
	public static String getXmlString(String xmlPath) {
		org.jdom2.Document document=null;
		try {  
            SAXBuilder reader = new SAXBuilder();   
            document=reader.build(new File(xmlPath));  
            Format format =Format.getPrettyFormat();      
            format.setEncoding("UTF-8");//设置编码格式   
              
            StringWriter out=null; //输出对象  
            String sReturn =""; //输出字符串  
            XMLOutputter outputter =new XMLOutputter();   
            out=new StringWriter();   
            outputter.output(document,out);  
            sReturn=out.toString();   
            return sReturn;  
       } catch (Exception e) {  
    	   e.printStackTrace();
           throw new BusinessException("xml文件转对象失败");
       }  
	}
	
	// 通过xml路径转成对象
	public static <T extends BaseEntity> T xmlFileToEntity(String xmlPath,Class<T> cls) {
		try {  
			String str=getXmlString(xmlPath);
			JAXBContext context = JAXBContext.newInstance(cls);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            T obj = (T)unmarshaller.unmarshal(new StringReader(str));  
            return obj;
        } catch (JAXBException e) {  
        	e.printStackTrace();
            throw new BusinessException("xml转对象失败");
        }  
	}
	
	/**
	 * 注意，对应的实体类上需要加上注解@XmlRootElement
	 */
	public static <T extends BaseEntity> String entityToXml(T obj) {
		try {  
			 JAXBContext context = JAXBContext.newInstance(obj.getClass());  
	         Marshaller marshaller = context.createMarshaller();  
	         marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
             marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
             marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
             StringWriter writer = new StringWriter();
             marshaller.marshal(obj, writer);
             return writer.toString();
        } catch (JAXBException e) {  
        	e.printStackTrace();
            throw new BusinessException("对象转xml失败");
        }  
	}
	
}
