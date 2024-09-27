package com.quiz_platform.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackages = "com.quiz_platform.authenticationdb.repository", // Package for authdb repositories
        entityManagerFactoryRef = "authDbEntityManagerFactory",
        transactionManagerRef = "authDbTransactionManager"
)
public class AuthenticationDbConfig {

    @Bean(name = "authDbDataSource")
    @ConfigurationProperties(prefix = "authdb.datasource")
    public DataSource authDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "authDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean authDbEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("authDbDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.quiz_platform.authenticationdb.entity") // Package for authdb entities
                .persistenceUnit("authDbPU")
                .build();
    }

    @Bean(name = "authDbTransactionManager")
    public PlatformTransactionManager authDbTransactionManager(
            @Qualifier("authDbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
