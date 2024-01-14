package com.xjt.source.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("account")) {
            Account account = (Account) bean;
            account.setName("ffbbb");
            return account;
        }
        return bean;
    }
}
