package com.quiz_platform.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
public class AuthenticationDbConfig {

    @Primary
    @Bean(name = "authDbDataSource")
    @ConfigurationProperties(prefix = "authdb.datasource")
    public DataSource authDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "authDbTransactionManager")
    public PlatformTransactionManager authDbTransactionManager() {
        return new DataSourceTransactionManager(authDbDataSource());
    }
}
