package com.poo0054.study.aop;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.lang.Nullable;

/**
 * @author poo00
 */
public class ProxyFactoryTest {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new SimpleServiceImpl());
        // proxyFactory.setInterfaces(SimpleService.class);
        proxyFactory.setAopProxyFactory(new DefaultAopProxyFactory());
        TestBeanAdvisor testBeanAdvisor = new TestBeanAdvisor();
        proxyFactory.addAdvisors(testBeanAdvisor);
        Object proxy = proxyFactory.getProxy();
        String send = ((SimpleService)proxy).send();
        System.out.println(testBeanAdvisor.count + "\t" + send);
    }

}

class TestBeanAdvisor extends StaticMethodMatcherPointcutAdvisor implements Serializable {

    private static final long serialVersionUID = 1917255554909574730L;

    public int count;

    public TestBeanAdvisor() {
        setAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, @Nullable Object target) {
                System.out.println("i before ");
                ++count;
            }
        });
    }

    @Override
    public boolean matches(Method method, @Nullable Class<?> targetClass) {
        return SimpleService.class.isAssignableFrom(targetClass);
    }

}