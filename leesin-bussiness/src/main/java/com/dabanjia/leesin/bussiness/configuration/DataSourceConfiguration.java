package com.dabanjia.leesin.bussiness.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author xujiajun
 * @since 2019/12/23
 */
@Configuration
@MapperScan(basePackages = "com.dabanjia.leesin.bussiness.dao", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfiguration {

	private static final String MAPPER_LOCATION = "classpath*:com/dabanjia/leesin/bussiness/mapper/*.xml";

	@Bean
	@ConfigurationProperties(prefix = "leesin.datasource")
	public DataSource dataSource() {
		return new DruidDataSource();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactory.setMapperLocations(patternResolver.getResources(MAPPER_LOCATION));
		return sqlSessionFactory.getObject();
	}

	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
