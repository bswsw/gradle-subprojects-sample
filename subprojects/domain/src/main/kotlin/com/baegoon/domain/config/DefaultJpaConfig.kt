package com.baegoon.domain.config

import com.baegoon.domain.config.properties.MultipleDataSourceProperties
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@EnableJpaRepositories(basePackages = ["com.baegoon.domain"])
@EnableJpaAuditing
class DefaultJpaConfig(
    private val multipleDataSourceProperties: MultipleDataSourceProperties
) {

    companion object {
        private const val IMPLICIT_KEY = "hibernate.implicit_naming_strategy"
        private const val PHYSICAL_KEY = "hibernate.physical_naming_strategy"
        private const val QUOTED_KEY = "hibernate.globally_quoted_identifiers"
        private const val LAZY_LOAD_KEY = "hibernate.enable_lazy_load_no_trans"
        private const val DIALECT_KEY = "hibernate.dialect"
        private const val DDL_AUTO_KEY = "hibernate.hbm2ddl.auto"
    }

    private val hibernateProperties = mapOf(
        IMPLICIT_KEY to SpringImplicitNamingStrategy::class.java.name,
        PHYSICAL_KEY to SpringPhysicalNamingStrategy::class.java.name,
        QUOTED_KEY to true,
        LAZY_LOAD_KEY to true,
        DIALECT_KEY to multipleDataSourceProperties.default.dialect,
        DDL_AUTO_KEY to multipleDataSourceProperties.default.ddlAuto
    )

    @Bean
    @ConfigurationProperties("multiple-datasources.default")
    fun defaultDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        defaultDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(defaultDataSource)
            .packages(multipleDataSourceProperties.default.entityPackage)
            .persistenceUnit(multipleDataSourceProperties.default.persistenceUnit)
            .properties(this.hibernateProperties)
            .build()
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}
