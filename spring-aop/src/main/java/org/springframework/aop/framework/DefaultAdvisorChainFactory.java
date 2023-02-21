/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package org.springframework.aop.framework;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aopalliance.intercept.Interceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.*;
import org.springframework.aop.framework.adapter.AdvisorAdapterRegistry;
import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry;
import org.springframework.lang.Nullable;

/**
 * A simple but definitive way of working out an advice chain for a Method, given an {@link Advised} object. Always
 * rebuilds each advice chain; caching can be provided by subclasses.
 *
 * @author Juergen Hoeller
 * @author Rod Johnson
 * @author Adrian Colyer
 * @since 2.0.3
 */
@SuppressWarnings("serial")
public class DefaultAdvisorChainFactory implements AdvisorChainFactory, Serializable {

    /**
     * Determine whether the Advisors contain matching introductions.
     */
    private static boolean hasMatchingIntroductions(Advisor[] advisors, Class<?> actualClass) {
        for (Advisor advisor : advisors) {
            if (advisor instanceof IntroductionAdvisor) {
                IntroductionAdvisor ia = (IntroductionAdvisor)advisor;
                if (ia.getClassFilter().matches(actualClass)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Advised config, Method method,
        @Nullable Class<?> targetClass) {

        // This is somewhat tricky... We have to process introductions first,
        // but we need to preserve order in the ultimate list.
        // 这有点棘手......我们必须先处理介绍，但我们需要保持最终列表中的顺序。

        // 获取注册器，这是一个单例模式的实现
        AdvisorAdapterRegistry registry = GlobalAdvisorAdapterRegistry.getInstance();
        // 获取所有的 Advisor
        Advisor[] advisors = config.getAdvisors();

        // Advisor链 已经在传进来的 config 中持有了，这里可以直接使用。
        // Advisor 中持有 切面Pointcut 和 增强行为Advice 两个重要属性
        List<Object> interceptorList = new ArrayList<>(advisors.length);
        Class<?> actualClass = (targetClass != null ? targetClass : method.getDeclaringClass());
        Boolean hasIntroductions = null;

        // 处理不同类的 advisor
        for (Advisor advisor : advisors) {
            // advisor 如果是 PointcutAdvisor 的实例
            if (advisor instanceof PointcutAdvisor) {
                // Add it conditionally.
                // 有条件地添加它。
                PointcutAdvisor pointcutAdvisor = (PointcutAdvisor)advisor;
                if (config.isPreFiltered() || pointcutAdvisor.getPointcut().getClassFilter().matches(actualClass)) {
                    MethodMatcher mm = pointcutAdvisor.getPointcut().getMethodMatcher();
                    boolean match;
                    if (mm instanceof IntroductionAwareMethodMatcher) {
                        if (hasIntroductions == null) {
                            hasIntroductions = hasMatchingIntroductions(advisors, actualClass);
                        }
                        match = ((IntroductionAwareMethodMatcher)mm).matches(method, actualClass, hasIntroductions);
                    } else {
                        match = mm.matches(method, actualClass);
                    }
                    if (match) {
                        // 符合条件的添加
                        MethodInterceptor[] interceptors = registry.getInterceptors(advisor);
                        // 是否为动态的
                        if (mm.isRuntime()) {
                            // Creating a new object instance in the getInterceptors() method
                            // isn't a problem as we normally cache created chains.
                            // 在 getInterceptors() 方法中创建一个新的对象实例不是问题，因为我们通常会缓存创建的链。
                            for (MethodInterceptor interceptor : interceptors) {
                                interceptorList.add(new InterceptorAndDynamicMethodMatcher(interceptor, mm));
                            }
                        } else {
                            interceptorList.addAll(Arrays.asList(interceptors));
                        }
                    }
                }
            } else if (advisor instanceof IntroductionAdvisor) {
                IntroductionAdvisor ia = (IntroductionAdvisor)advisor;
                if (config.isPreFiltered() || ia.getClassFilter().matches(actualClass)) {
                    Interceptor[] interceptors = registry.getInterceptors(advisor);
                    interceptorList.addAll(Arrays.asList(interceptors));
                }
            } else {
                Interceptor[] interceptors = registry.getInterceptors(advisor);
                interceptorList.addAll(Arrays.asList(interceptors));
            }
        }

        return interceptorList;
    }

}
