package com.poo0054.study.transaction;

import com.poo0054.study.transaction.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author poo00
 */
public class Service {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public List<SysMenu> cacheTest(String str) {
		List<SysMenu> sysMenus = jdbcTemplate.query("select * from sys_menu ", BeanPropertyRowMapper.newInstance(SysMenu.class));
		return sysMenus;
	}

}
