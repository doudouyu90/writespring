package com.writespring.beans.factory.support;

import com.writespring.beans.factory.BeanDefinition;
import com.writespring.beans.factory.BeanFactory;
import com.writespring.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory {

    private static  String ID ="id";
    private  static  String CLASS_NAME = "class";
    Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,BeanDefinition>();

    //解析xml
    public DefaultBeanFactory(String configFile) {
            loadBeanDefinition(configFile);
    }

    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    public Object getBean(String beanId) {
        try {
            String className = beanDefinitionMap.get(beanId).getBeanClassName();
           return  Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public void loadBeanDefinition(String configFile){
        InputStream is = ClassUtils.classLoader().getResourceAsStream(configFile);
        //创建DOM4J解析对象
        SAXReader reader = new SAXReader();
        try {
        Document  doc = reader.read(is);
        Element rootElement  =  doc.getRootElement();
        Iterator<Element> iter = rootElement.elementIterator();
        //把id,类对象存入map中
          while(iter.hasNext()){
                Element element = iter.next();
                String id =element.attributeValue(ID);
                String className = element.attributeValue(CLASS_NAME);
                BeanDefinition bd = new GenericBeanDefinition(id,className);
                beanDefinitionMap.put(id,bd);
          }
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
