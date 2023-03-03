package com.poo0054.study.mvc;

import com.poo0054.study.mvc.config.Config;
import org.apache.catalina.*;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.startup.Tomcat;
import org.junit.jupiter.api.Test;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

/**
 * tomcat>service>connector,Engine   Engine>host>Context
 *
 * @author zhangzhi
 * @date 2023/3/1
 */
public class TomcatStartTest {


	@Test
	public void test() throws LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8889);
		//看源码发现,他只能设置一个service,直接拿默认的
		Service service = tomcat.getService();
		service.setName("myTomcat");
		tomcat.getConnector();
		/*//创建连接器,并且添加对应的连接器,同时连接器指定端口
		Connector connector = new Connector();
		connector.setPort(8880);
		service.addConnector(connector);*/
		//创建一个引擎,放入service中
		Engine engine = new StandardEngine();
		engine.setDefaultHost("localhost");
		engine.setName("myTomcat");
		service.setContainer(engine);
		//添加host
		StandardHost host = new StandardHost();
		host.setName("localhost");
		host.setAppBase("work");
		engine.addChild(host);
		//在对应的host下面创建一个 standardContext 并制定他的工作路径
		Context context = tomcat.addContext(host, "", null);
		//ContextLoaderListener 中获取的属性
		context.addParameter("contextConfigLocation", "classpath:applicationContext.xml");
		context.addApplicationListener("org.springframework.web.context.ContextLoaderListener");
//		servletContext.setInitParameter("contextClass", "applicationContext.xml");
		//创建一个servlet
		StandardWrapper wrapper = new StandardWrapper();
//		DispatcherServlet servlet = new DispatcherServlet();
//		servlet.setContextConfigLocation("classpath:applicationContext.xml");
//		wrapper.setServlet(servlet);
		wrapper.setServletClass("org.springframework.web.servlet.DispatcherServlet");
		//当前属性的参数
		wrapper.addInitParameter("contextConfigLocation", "classpath:applicationContext.xml");
		wrapper.setLoadOnStartup(1);
		context.addChild(wrapper);
		wrapper.setName("mvcServlet");
		wrapper.addMapping("/");
		//tomcat启动
		tomcat.start();
		//保持主线程不退出
		tomcat.getServer().await();
	}

	@Test
	public void test1() throws LifecycleException {
		//创建tomcat对象
		Tomcat tomcat = new Tomcat();
		//设置端口
		tomcat.setPort(8088);
		tomcat.getConnector();
		//创建web容器上下文
		Context context = tomcat.addContext("", null);
		//注册前端控制器
		DispatcherServlet dispatcherServlet = new DispatcherServlet(
				this.createApplicationContext(context.getServletContext()));
		Wrapper servlet = tomcat.addServlet("", "dispatcherServlet", dispatcherServlet);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");
		tomcat.start();
		//保持主线程不退出
		tomcat.getServer().await();
	}

	private WebApplicationContext createApplicationContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext ctx
				= new AnnotationConfigWebApplicationContext();
		//加载配置类
		ctx.register(Config.class);
		ctx.setServletContext(servletContext);
		ctx.refresh();
		ctx.registerShutdownHook();
		return ctx;
	}

}

