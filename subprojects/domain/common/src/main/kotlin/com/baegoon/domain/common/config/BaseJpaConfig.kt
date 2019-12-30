package com.baegoon.domain.common.config

import com.baegoon.domain.common.properties.JpaProperties
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@EnableJpaAuditing
abstract class BaseJpaConfig {

    companion object {
        const val REPOSITORY_PACKAGE_NAME_PREFIX = "com.baegoon.domain." // custom
        const val PERSISTENCE_UNIT_NAME_SUFFIX = "db" // custom

        // fixed constants
        const val JPA_PROPERTIES_SUFFIX = "-jpa"
        const val JPA_PROPERTIES_BEAN_SUFFIX = "JpaProperties"
        const val DATA_SOURCE_PROPERTIES_SUFFIX = ".datasource"
        const val DATA_SOURCE_PROPERTIES_BEAN_SUFFIX = "DataSourceProperties"
        const val DATA_SOURCE_BEAN_NAME_SUFFIX = "DataSource"
        const val ENTITY_MANAGER_FACTORY_BEAN_NAME_SUFFIX = "EntityManagerFactory"
        const val TRANSACTION_MANAGER_BEAN_NAME_SUFFIX = "TransactionManager"
    }

    open fun jpaProperties(): JpaProperties {
        return JpaProperties()
    }

    open fun dataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    open fun dataSource(dataSourceProperties: DataSourceProperties): DataSource {
        return dataSourceProperties.initializeDataSourceBuilder().build()
    }

    open fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        dataSource: DataSource,
        jpaProperties: JpaProperties
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(dataSource)
            .packages(this.entityPackage())
            .persistenceUnit(this.persistenceUnit())
            .properties(
                mapOf(
                    "hibernate.implicit_naming_strategy" to SpringImplicitNamingStrategy::class.java.name,
                    "hibernate.physical_naming_strategy" to SpringPhysicalNamingStrategy::class.java.name,
                    "hibernate.globally_quoted_identifiers" to true,
                    "hibernate.enable_lazy_load_no_trans" to true,
                    "hibernate.dialect" to jpaProperties.dialect,
                    "hibernate.hbm2ddl.auto" to jpaProperties.ddlAuto,
                    "hibernate.discriminator.ignore_explicit_for_joined" to true
                )
            )
            .build()
    }

    open fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

    abstract fun persistenceUnit(): String

    abstract fun entityPackage(): String
}
