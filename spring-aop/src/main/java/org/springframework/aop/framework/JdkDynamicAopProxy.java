/*
 * Copyright 2002-2019 the original author or authors.
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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AopInvocationException;
import org.springframework.aop.RawTargetAccess;
import org.springframework.aop.TargetSource;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.DecoratingProxy;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * JDK-based {@link AopProxy} implementation for the Spring AOP framework, based on JDK {@link java.lang.reflect.Proxy
 * dynamic proxies}.
 *
 * <p>
 * Creates a dynamic proxy, implementing the interfaces exposed by the AopProxy. Dynamic proxies <i>cannot</i> be used
 * to proxy methods defined in classes, rather than interfaces.
 *
 * <p>
 * Objects of this type should be obtained through proxy factories, configured by an {@link AdvisedSupport} class. This
 * class is internal to Spring's AOP framework and need not be used directly by client code.
 *
 * <p>
 * Proxies created using this class will be thread-safe if the underlying (target) class is thread-safe.
 *
 * <p>
 * Proxies are serializable so long as all Advisors (including Advices and Pointcuts) and the TargetSource are
 * serializable.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Dave Syer
 * @see java.lang.reflect.Proxy
 * @see AdvisedSupport
 * @see ProxyFactory
 */
final class JdkDynamicAopProxy implements AopProxy, InvocationHandler, Serializable {

    /**
     * use serialVersionUID from Spring 1.2 for interoperability.
     */
    private static final long serialVersionUID = 5531744639992436476L;

    /*
     * NOTE: We could avoid the code duplication between this class and the CGLIB
     * proxies by refactoring "invoke" into a template method. However, this approach
     * adds at least 10% performance overhead versus a copy-paste solution, so we sacrifice
     * elegance for performance. (We have a good test suite to ensure that the different
     * proxies behave the same :-)
     * This way, we can also more easily take advantage of minor optimizations in each class.
     */

    /**
     * We use a static Log to avoid serialization issues.
     */
    private static final Log logger = LogFactory.getLog(JdkDynamicAopProxy.class);

    /**
     * Config used to configure this proxy.
     */
    private final AdvisedSupport advised;

    /**
     * Is the {@link #equals} method defined on the proxied interfaces?
     */
    private boolean equalsDefined;

    /**
     * Is the {@link #hashCode} method defined on the proxied interfaces?
     */
    private boolean hashCodeDefined;

    /**
     * Construct a new JdkDynamicAopProxy for the given AOP configuration.
     *
     * @param config the AOP configuration as AdvisedSupport object
     * @throws AopConfigException if the config is invalid. We try to throw an informative exception in this case,
     *             rather than let a mysterious failure happen later.
     */
    public JdkDynamicAopProxy(AdvisedSupport config) throws AopConfigException {
        Assert.notNull(config, "AdvisedSupport must not be null");
        if (config.getAdvisors().length == 0 && config.getTargetSource() == AdvisedSupport.EMPTY_TARGET_SOURCE) {
            throw new AopConfigException("No advisors and no TargetSource specified");
        }
        // 这个 advised 是一个 AdvisedSupport 对象，可以通过它获取被代理对象 target
        // 这样，当 invoke()方法 被 代理对象aopProxy 调用时，就可以调用 target 的目标方法了
        this.advised = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(ClassUtils.getDefaultClassLoader());
    }

    @Override
    public Object getProxy(@Nullable ClassLoader classLoader) {
        if (logger.isTraceEnabled()) {
            logger.trace("Creating JDK dynamic proxy: " + this.advised.getTargetSource());
        }
        // 获取代理类要实现的接口
        Class<?>[] proxiedInterfaces = AopProxyUtils.completeProxiedInterfaces(this.advised, true);
        findDefinedEqualsAndHashCodeMethods(proxiedInterfaces);
        // 通过 java.lang.reflect.Proxy 生成代理对象并返回
        return Proxy.newProxyInstance(classLoader, proxiedInterfaces, this);
    }

    /**
     * Finds any {@link #equals} or {@link #hashCode} method that may be defined on the supplied set of interfaces.
     *
     * @param proxiedInterfaces the interfaces to introspect
     */
    private void findDefinedEqualsAndHashCodeMethods(Class<?>[] proxiedInterfaces) {
        // 查找可以在提供的接口集上定义的任何equals或hashCode方法。
        for (Class<?> proxiedInterface : proxiedInterfaces) {
            // 获取所有方法
            Method[] methods = proxiedInterface.getDeclaredMethods();
            for (Method method : methods) {
                if (AopUtils.isEqualsMethod(method)) {
                    this.equalsDefined = true;
                }
                if (AopUtils.isHashCodeMethod(method)) {
                    this.hashCodeDefined = true;
                }
                if (this.equalsDefined && this.hashCodeDefined) {
                    return;
                }
            }
        }
    }

    /**
     * Implementation of {@code InvocationHandler.invoke}.
     * <p>
     * Callers will see exactly the exception thrown by the target, unless a hook method throws an exception.
     */
    @Override
    @Nullable
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /*
        aop jdk的代理执行地方
         */
        Object oldProxy = null;
        boolean setProxyContext = false;

        // 通过 targetSource 可以获取被代理对象 目标实例
        TargetSource targetSource = this.advised.targetSource;
        Object target = null;

        try {
            if (!this.equalsDefined && AopUtils.isEqualsMethod(method)) {
                // The target does not implement the equals(Object) method itself.
                // 目标不实现 equals(Object) 方法本身。
                return equals(args[0]);
            } else if (!this.hashCodeDefined && AopUtils.isHashCodeMethod(method)) {
                // The target does not implement the hashCode() method itself.
                // 目标不实现 hashCode() 方法本身。
                return hashCode();
            } else if (method.getDeclaringClass() == DecoratingProxy.class) {
                // There is only getDecoratedClass() declared -> dispatch to proxy config.
                // 这里只声明了 getDecoratedClass() -> 分派到代理配置。
                return AopProxyUtils.ultimateTargetClass(this.advised);
            } else if (!this.advised.opaque && method.getDeclaringClass().isInterface()
                && method.getDeclaringClass().isAssignableFrom(Advised.class)) {
                // Service invocations on ProxyConfig with the proxy config...
                // 使用代理配置对 ProxyConfig 进行服务调用...
                return AopUtils.invokeJoinpointUsingReflection(this.advised, method, args);
            }

            Object retVal;

            if (this.advised.exposeProxy) {
                // Make invocation available if necessary.
                // 如有必要，使调用可用。
                // 是否开启了当前线程获取当前代理对象
                oldProxy = AopContext.setCurrentProxy(proxy);
                setProxyContext = true;
            }

            // Get as late as possible to minimize the time we "own" the target,
            // in case it comes from a pool.
            // 尽可能晚一点，以尽量减少我们“拥有”目标的时间，以防它来自 池。`

            // 获取目标对象，为目标方法的调用做准备
            target = targetSource.getTarget();
            Class<?> targetClass = (target != null ? target.getClass() : null);

            // Get the interception chain for this method.
            // 获取此方法的拦截链。
            // 获取定义好的拦截器链，即 Advisor列表
            List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);

            // Check whether we have any advice. If we don't, we can fallback on direct
            // reflective invocation of the target, and avoid creating a MethodInvocation.
            /*
            检查我们是否有任何建议。如果我们不这样做，我们可以回退到目标的直接反射调用，并避免创建 MethodInvocation。
             */

            // 是否有advised
            // 如果没有配置拦截器，就直接通过反射调用目标对象 target 的 method对象，并获取返回值
            if (chain.isEmpty()) {
                // We can skip creating a MethodInvocation: just invoke the target directly
                // Note that the final invoker must be an InvokerInterceptor so we know it does
                // nothing but a reflective operation on the target, and no hot swapping or fancy proxying.
                /*
                我们可以跳过创建 MethodInvocation：直接调用目标请注意，最终调用者必须是 InvokerInterceptor，
                因此我们知道它只对目标执行反射操作，并且没有热交换或花哨的代理。
                 */
                Object[] argsToUse = AopProxyUtils.adaptArgumentsIfNecessary(method, args);
                // 没有拦截器 直接调用
                retVal = AopUtils.invokeJoinpointUsingReflection(target, method, argsToUse);
            } else {
                // We need to create a method invocation...
                // 我们需要创建一个方法调用...

                // 如果有拦截器链，则需要先调用拦截器链中的拦截器，再调用目标的对应方法
                // 这里通过构造 ReflectiveMethodInvocation 来实现
                MethodInvocation invocation =
                    new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain);
                // Proceed to the joinpoint through the interceptor chain.
                // 通过拦截器链前往连接点。 aop代理执行之前的拦截器
                retVal = invocation.proceed();
            }

            // Massage return value if necessary.
            // 获取 method 返回值的类型
            Class<?> returnType = method.getReturnType();
            if (retVal != null && retVal == target && returnType != Object.class && returnType.isInstance(proxy)
                && !RawTargetAccess.class.isAssignableFrom(method.getDeclaringClass())) {
                // Special case: it returned "this" and the return type of the method
                // is type-compatible. Note that we can't help if the target sets
                // a reference to itself in another returned object.
                /*
                特殊情况：它返回“this”并且方法的返回类型是类型兼容的。请注意，如果目标在另一个返回的对象中设置对自身的引用，我们将无能为力。
                 */
                retVal = proxy;
            } else if (retVal == null && returnType != Void.TYPE && returnType.isPrimitive()) {
                throw new AopInvocationException(
                    "Null return value from advice does not match primitive return type for: " + method);
            }
            return retVal;
        } finally {
            if (target != null && !targetSource.isStatic()) {
                // Must have come from TargetSource.
                // 必须来自 TargetSource。
                targetSource.releaseTarget(target);
            }
            if (setProxyContext) {
                // Restore old proxy.
                // 恢复旧代理。
                AopContext.setCurrentProxy(oldProxy);
            }
        }
    }

    /**
     * Equality means interfaces, advisors and TargetSource are equal.
     * <p>
     * The compared object may be a JdkDynamicAopProxy instance itself or a dynamic proxy wrapping a JdkDynamicAopProxy
     * instance.
     */
    @Override
    public boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }

        JdkDynamicAopProxy otherProxy;
        if (other instanceof JdkDynamicAopProxy) {
            otherProxy = (JdkDynamicAopProxy)other;
        } else if (Proxy.isProxyClass(other.getClass())) {
            InvocationHandler ih = Proxy.getInvocationHandler(other);
            if (!(ih instanceof JdkDynamicAopProxy)) {
                return false;
            }
            otherProxy = (JdkDynamicAopProxy)ih;
        } else {
            // Not a valid comparison...
            return false;
        }

        // If we get here, otherProxy is the other AopProxy.
        return AopProxyUtils.equalsInProxy(this.advised, otherProxy.advised);
    }

    /**
     * Proxy uses the hash code of the TargetSource.
     */
    @Override
    public int hashCode() {
        return JdkDynamicAopProxy.class.hashCode() * 13 + this.advised.getTargetSource().hashCode();
    }

}
