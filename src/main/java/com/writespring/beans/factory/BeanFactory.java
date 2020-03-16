package com.writespring.beans.factory;

public interface BeanFactory {
    BeanDefinition getBeanDefinition(String firstBean);

    Object getBean(String firstBean);
}
