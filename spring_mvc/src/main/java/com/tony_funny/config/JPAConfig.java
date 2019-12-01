package com.tony_funny.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.tony_funny.repository") 
@EnableTransactionManagement
public class JPAConfig {

	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean en = new LocalContainerEntityManagerFactoryBean();
		en.setDataSource(dataSource());
		en.setPersistenceUnitName("persistence-data");
		JpaVendorAdapter vd = new HibernateJpaVendorAdapter();
		en.setJpaVendorAdapter(vd);
		en.setJpaProperties(jpaProperties());
		return en;
	}
	
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager trans = new JpaTransactionManager();
		trans.setEntityManagerFactory(entityManagerFactory);
		return trans;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTransaction() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataS = new DriverManagerDataSource();
		dataS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataS.setUrl("jdbc:mysql://localhost:3306/springdatajpa");
		dataS.setUsername("root");
		dataS.setPassword("tuandeptrai");
		return dataS;
	}
	
	public Properties jpaProperties() {
		Properties pro = new Properties();
//		pro.setProperty("hibernate.hbm2ddl.auto", "update");
//		pro.setProperty("hibernate.hbm2ddl.auto", "create");
		pro.setProperty("hibernate.hbm2ddl.auto", "none");
		pro.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		return pro;
	}
	
	
	
}
