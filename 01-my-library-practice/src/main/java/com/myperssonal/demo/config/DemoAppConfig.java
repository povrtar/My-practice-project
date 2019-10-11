package com.myperssonal.demo.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:persistance-mysql.properties")
public class DemoAppConfig {
	@Autowired
	private Environment env;
	private Logger logger=LoggerFactory.getLogger( DemoAppConfig.class);
@Bean
public DataSource securityDataSource() {
	ComboPooledDataSource securityDataSource=
			new ComboPooledDataSource();
	try {
		securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
	} catch (PropertyVetoException exc) {
	throw new RuntimeException(exc);
	}
	logger.info(">>>jdbc.url="+env.getProperty("jdbc.url"));
	logger.info(">>>jdbc.user="+env.getProperty("jdbc.user"));
	
	securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
	securityDataSource.setUser(env.getProperty("jdbc.user"));
	securityDataSource.setPassword(env.getProperty("jdbc.password"));
	securityDataSource.setInitialPoolSize(
			getIntProperty(
	"connection.pool.initialPoolSize"));
	securityDataSource.setMinPoolSize(
			getIntProperty(
	"connection.pool.minPoolSize"));
	securityDataSource.setMaxPoolSize(
			getIntProperty(
	"connection.pool.maxPoolSize"));
	securityDataSource.setMaxIdleTime(
			getIntProperty(
	"connection.pool.maxIdleTime"));
	return securityDataSource;
}
private int getIntProperty(String propName) {
	String propVal=env.getProperty(propName);
	int intPropVal=Integer.parseInt(propVal);
	return intPropVal;
}

}