package com.ndk.droolsmicroservice.kieinfo.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ndk.droolsmicroservice.kieinfo.repo", entityManagerFactoryRef = "kieInfoEntityManagerFactory", transactionManagerRef = "kieInfoTransactionManager")
public class PersistenceKieFilesInfo {

	@Bean(name = "kieInfoDataSource")
	@ConfigurationProperties(prefix = "kieinfo.datasource")
	public DataSource kieInfoDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean kieInfoEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("kieInfoDataSource") DataSource kieInfoDataSource) {
		return builder.dataSource(kieInfoDataSource).packages("com.ndk.droolsmicroservice.kieinfo.model")
				.persistenceUnit("kieinfo").build();
	}

	@Bean
	public PlatformTransactionManager kieInfoTransactionManager(
			@Qualifier("kieInfoEntityManagerFactory") EntityManagerFactory kieInfoEntityManagerFactory) {
		return new JpaTransactionManager(kieInfoEntityManagerFactory);
	}
}
