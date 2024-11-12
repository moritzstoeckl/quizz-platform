package com.quiz_platform.config;

import jakarta.persistence.EntityManagerFactory;
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

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.quiz_platform.storingdb.repository",
        entityManagerFactoryRef = "storingDbEntityManagerFactory",
        transactionManagerRef = "storingDbTransactionManager"
)
public class StoringDbConfig {

    /**
     * Creates and configures the primary DataSource bean for the storing database.
     *
     * @return DataSource for the storing database.
     */
    @Primary
    @Bean(name = "storingDbDataSource")
    @ConfigurationProperties(prefix = "storingdb.datasource")
    public DataSource storingDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Creates and configures the primary EntityManagerFactory bean for the storing database.
     * This sets up entity scanning for the storing database entities.
     *
     * @param builder    the EntityManagerFactoryBuilder used to build the factory.
     * @param dataSource the storing database DataSource.
     * @return LocalContainerEntityManagerFactoryBean for managing entities in the storing database.
     */
    @Primary
    @Bean(name = "storingDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean storingDbEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("storingDbDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.quiz_platform.storingdb.entity")
                .persistenceUnit("storingDbPU")
                .build();
    }

    /**
     * Creates and configures the primary TransactionManager bean for the storing database.
     * This transaction manager handles transactions for the storing database's entity manager factory.
     *
     * @param entityManagerFactory the EntityManagerFactory for the storing database.
     * @return PlatformTransactionManager for managing transactions on the storing database.
     */
    @Primary
    @Bean(name = "storingDbTransactionManager")
    public PlatformTransactionManager storingDbTransactionManager(
            @Qualifier("storingDbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
