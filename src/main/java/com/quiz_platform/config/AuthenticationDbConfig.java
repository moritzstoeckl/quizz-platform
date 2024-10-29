package com.quiz_platform.config;

import jakarta.persistence.EntityManagerFactory;
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

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.quiz_platform.authenticationdb.repository",
        entityManagerFactoryRef = "authDbEntityManagerFactory",
        transactionManagerRef = "authDbTransactionManager"
)
public class AuthenticationDbConfig {

    /**
     * Configures the data source for the authentication database using properties prefixed with "authdb.datasource".
     *
     * @return the configured DataSource for the authentication database.
     */
    @Bean(name = "authDbDataSource")
    @ConfigurationProperties(prefix = "authdb.datasource")
    public DataSource authDbDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Creates the EntityManagerFactory for the authentication database to handle persistence unit settings.
     *
     * @param builder    the EntityManagerFactoryBuilder used for building the factory.
     * @param dataSource the DataSource for the authentication database.
     * @return a LocalContainerEntityManagerFactoryBean for the authentication database.
     */
    @Bean(name = "authDbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean authDbEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("authDbDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.quiz_platform.authenticationdb.entity")
                .persistenceUnit("authDbPU")
                .build();
    }

    /**
     * Sets up the transaction manager for the authentication database, associating it with the
     * authentication database EntityManagerFactory.
     *
     * @param entityManagerFactory the EntityManagerFactory for the authentication database.
     * @return the PlatformTransactionManager for managing transactions on the authentication database.
     */
    @Bean(name = "authDbTransactionManager")
    public PlatformTransactionManager authDbTransactionManager(
            @Qualifier("authDbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
