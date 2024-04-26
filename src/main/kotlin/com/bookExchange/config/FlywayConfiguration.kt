package com.bookExchange.config

import org.flywaydb.core.Flyway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(value = [FlywayProperties::class])
class FlywayConfiguration {
    private val log: Logger = LoggerFactory.getLogger(FlywayConfiguration::class.java)

    @Bean(initMethod = "migrate")
    fun flyway(flywayProperties: FlywayProperties): Flyway? {
        log.info(
            "Flyway configuration - url: {}, user: {}, password: {}, isBaselineOnMigrate: {}, isOutOfOrder: {}, locations: {}",
            flywayProperties.url,
            flywayProperties.user,
            flywayProperties.password,
            flywayProperties.isBaselineOnMigrate,
            flywayProperties.isOutOfOrder,
            flywayProperties.locations
        )
        return Flyway.configure()
            .dataSource(
                flywayProperties.url,
                flywayProperties.user,
                flywayProperties.password
            )
            .locations(*flywayProperties.locations.toTypedArray())
            .baselineOnMigrate(flywayProperties.isBaselineOnMigrate)
            .outOfOrder(flywayProperties.isOutOfOrder)
            .load()
    }
}