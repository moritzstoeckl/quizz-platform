package com.quiz_platform.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.quiz_platform.storingdb.repository", // Package for storing repositories
        entityManagerFactoryRef = "storingDbEntityManagerFactory",
        transactionManagerRef = "storingDbTransactionManager"
)
public class StoringDbConfig {

    @Primary
    @Bean(name = "storingDbDataSource")
    @ConfigurationProperties(prefix = "storingdb.datasource")
    public DataSource storingDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "storingDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean storingDbEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("storingDbDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.quiz_platform.storingdb.entity") // Package for storing entities
                .persistenceUnit("storingDbPU")
                .build();
    }

    @Primary
    @Bean(name = "storingDbTransactionManager")
    public PlatformTransactionManager storingDbTransactionManager(
            @Qualifier("storingDbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
