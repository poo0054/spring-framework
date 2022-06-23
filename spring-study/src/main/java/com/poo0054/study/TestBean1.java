package com.poo0054.study;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

/**
 * {@link BeanFactory}
 *
 * @author zhangzhi
 * @version 1.0
 * @since 2022/6/22 17:21
 */

public class TestBean1 implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware
		, EmbeddedValueResolverAware, ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware
		, ApplicationContextAware, BeanPostProcessor, InitializingBean
		, DestructionAwareBeanPostProcessor, DisposableBean {


	private TestBean testBean;

	public void setTestBean(TestBean testBean) {
		System.out.println("----TestBean1---setTestBean--------testBean--------------------------------------------------");
		this.testBean = testBean;
	}

	public TestBean1() {
		System.out.println("------TestBean1   init ---------");
	}

	public void send() {
		System.out.println("----TestBean---send----------------------------------------------------------");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("------setBeanName-" + name + "-----------");
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("------setBeanClassLoader-" + classLoader + "-----------");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("------setBeanFactory-" + beanFactory + "-----------");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("------destroy----------");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("------afterPropertiesSet-----------");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("------setApplicationContext-" + applicationContext + "-----------");
	}

	@Override
	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
		System.out.println("------postProcessBeforeDestruction-" + bean + "---------" + beanName + "-----------");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("------postProcessBeforeInitialization-" + bean + "---------" + beanName + "-----------");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("------postProcessAfterInitialization-" + bean + "---------" + beanName + "-----------");
		return bean;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		System.out.println("------setApplicationEventPublisher-" + applicationEventPublisher + "-----------");
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		System.out.println("------setEmbeddedValueResolver-" + resolver + "-----------");
	}

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("------setEnvironment-" + environment + "-----------");
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		System.out.println("------setMessageSource-" + messageSource + "-----------");
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		System.out.println("------setResourceLoader-" + resourceLoader + "-----------");
	}
}
