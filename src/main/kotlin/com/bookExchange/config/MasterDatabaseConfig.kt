package com.bookExchange.config

import com.bookExchange.annotation.ReadOnlyRepository
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@FlywayDataSource
@EnableJpaRepositories(
    "com.bookExchange.repository",
    excludeFilters = [ComponentScan.Filter(ReadOnlyRepository::class)],
    entityManagerFactoryRef = "masterEntityManagerFactory",
    transactionManagerRef = "masterTransactionManager"
)
class MasterDatabaseConfig {

    @Primary
    @Bean(name = ["masterDataSource"])
    @ConfigurationProperties("spring.datasource.master.hikari")
    fun masterDataSource() : DataSource {
        return DataSourceBuilder.create().build()
    }

    @Primary
    @Bean(name = ["masterEntityManagerFactory"])
    fun masterEntityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("masterDataSource") dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(dataSource)
            .packages("com.bookExchange.model")
            .persistenceUnit("master")
            .build()
    }

    @Primary
    @Bean(name = ["masterTransactionManager"])
    fun masterTransactionManager(
        @Qualifier("masterEntityManagerFactory") entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}