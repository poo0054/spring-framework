package com.poo0054.study.aop.ee;

import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author poo00
 */
public class ProxyFactoryTest {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new ServiceImpl());
        proxyFactory.setInterfaces(Service.class);
        proxyFactory.setAopProxyFactory(new DefaultAopProxyFactory());
        TestBeanAdvisor testBeanAdvisor = new TestBeanAdvisor();
        proxyFactory.addAdvisors(testBeanAdvisor);
        // 创建出代理对象
        Object proxy = proxyFactory.getProxy();
        String send = ((Service)proxy).send();
        System.out.println(testBeanAdvisor.count + "\t" + send);
    }

}
