package com.writespring.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.writespring.beans.factory.BeanDefinition;
import com.writespring.beans.factory.BeanFactory;
import com.writespring.beans.factory.support.DefaultBeanFactory;
import com.writespring.service.FirstService;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void testGetBean(){
        BeanFactory beanFactory = new DefaultBeanFactory("first_bean_v1.xml");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("firstBean");


        assertEquals("com.writespring.service.FirstService",beanDefinition.getBeanClassName());

        FirstService firstService = (FirstService)beanFactory.getBean("firstBean");

        assertNotNull(firstService);

    }
}
