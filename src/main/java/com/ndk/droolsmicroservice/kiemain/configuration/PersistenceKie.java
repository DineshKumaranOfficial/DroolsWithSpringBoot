package com.ndk.droolsmicroservice.kiemain.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ndk.droolsmicroservice.kiemain.repo", entityManagerFactoryRef = "kieEntityManagerFactory", transactionManagerRef = "kieTransactionManager")
public class PersistenceKie {

	@Bean(name = "kieDataSource")
	@Primary
	@ConfigurationProperties(prefix = "kiemain.datasource")
	public DataSource kieDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "kieEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean kieEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(kieDataSource()).packages("com.ndk.droolsmicroservice.kiemain.model")
				.persistenceUnit("kiemain").build();
	}

	@Bean
	@Primary
	public PlatformTransactionManager kieTransactionManager(
			@Qualifier("kieEntityManagerFactory") EntityManagerFactory kieEntityManagerFactory) {
		return new JpaTransactionManager(kieEntityManagerFactory);
	}
}
