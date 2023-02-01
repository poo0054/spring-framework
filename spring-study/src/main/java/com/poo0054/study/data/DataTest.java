package com.poo0054.study.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.poo0054.study.data.config.DataConfig;

/**
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/8/23 14:33
 */
public class DataTest {
    @Test
    void test() throws SQLException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
            new AnnotationConfigApplicationContext(DataConfig.class);
        DataSource dataSource = annotationConfigApplicationContext.getBean("dataSource", DataSource.class);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from sys_user");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            System.out.println(id);
        }
    }
}
