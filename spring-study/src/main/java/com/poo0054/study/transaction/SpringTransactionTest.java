package com.poo0054.study.transaction;

import com.poo0054.study.transaction.entity.SysMenu;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author zhangzhi
 * @date 2023/3/6
 */
public class SpringTransactionTest {

	@Test
	void test() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TransactionConfig.class);
		Service cacheTest = applicationContext.getBean(Service.class);
		List<SysMenu> sysMenus = cacheTest.cacheTest("i like");
		System.out.println(sysMenus);
	}


	@Test
	void transactionManagerTest() {
		TransactionConfig transactionConfig = new TransactionConfig();
		DataSource dataSource = transactionConfig.dataSource();
		DataSourceTransactionManager platformTransactionManager = new DataSourceTransactionManager();
		platformTransactionManager.setDataSource(dataSource);
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		// 获得事务状态
		TransactionStatus status = platformTransactionManager.getTransaction(defaultTransactionDefinition);
		try {
			// 数据库操作
			JdbcTemplate jdbcTemplate = transactionConfig.jdbcTemplate(dataSource);
			List<SysMenu> sysMenus = jdbcTemplate.query("select * from sys_menu ", BeanPropertyRowMapper.newInstance(SysMenu.class));
			System.out.println(sysMenus);
			// 提交
			platformTransactionManager.commit(status);
		} catch (Exception e) {
			// 回滚
			platformTransactionManager.rollback(status);
		}
	}
}

