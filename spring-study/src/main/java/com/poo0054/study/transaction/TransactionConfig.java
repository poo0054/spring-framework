package com.poo0054.study.transaction;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author ZhangZhi
 * @version 1.0
 * @date 2022/11/4 14:01
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {

	@Bean
	public Service service() {
		return new Service();
	}

	@Bean
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl("jdbc:mysql://192.168.56.10:3306/oms-system");
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("root");
		druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return druidDataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public TransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}