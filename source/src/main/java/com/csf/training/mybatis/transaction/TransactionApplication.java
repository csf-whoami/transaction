package com.csf.training.mybatis.transaction;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication(scanBasePackages={
		"com.csf.training.mybatis.transaction.*",
		"com.csf.training.mybatis.transaction.controller",
		"com.csf.training.mybatis.transaction.model",
		"com.csf.training.mybatis.transaction.mapper",
		"com.csf.training.mybatis.transaction.service",
		"com.csf.training.mybatis.transaction.service.*"})
@MapperScan(value = {"com.csf.training.mybatis.transaction.mapper"})
public class TransactionApplication {

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:db/mybatis/mapper/*Map.xml");
		sessionFactory.setMapperLocations(res);
		sessionFactory.setConfigLocation(applicationContext.getResource("classpath:db/mybatis/mybatis-config.xml"));
		return sessionFactory.getObject();
	}
}
