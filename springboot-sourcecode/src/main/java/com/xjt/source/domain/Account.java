package com.xjt.source.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Account implements InitializingBean, DisposableBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Account(String name){
        this.name=name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setName("bbfff");
    }

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("exit...##############################################");
    }
}
