package com.binchencoder.study.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring的context，封装成静态方法方便获取bean
 *
 * @author chenbin
 */
@Component
@SuppressWarnings("unchecked")
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    public static <T> T getBean(Class<T> classz) {
        return CONTEXT.getBean(classz);
    }

    public static <T> T getBean(String name, Class<T> type) {
        return (T) CONTEXT.getBean(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }
}
