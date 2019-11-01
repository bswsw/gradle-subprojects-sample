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
open class BaseJpaConfig {

    companion object {
        private const val IMPLICIT_KEY = "hibernate.implicit_naming_strategy"
        private const val PHYSICAL_KEY = "hibernate.physical_naming_strategy"
        private const val QUOTED_KEY = "hibernate.globally_quoted_identifiers"
        private const val LAZY_LOAD_KEY = "hibernate.enable_lazy_load_no_trans"
        private const val DIALECT_KEY = "hibernate.dialect"
        private const val DDL_AUTO_KEY = "hibernate.hbm2ddl.auto"
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
            .packages(jpaProperties.entityPackage)
            .persistenceUnit(jpaProperties.persistenceUnit)
            .properties(
                mapOf(
                    IMPLICIT_KEY to SpringImplicitNamingStrategy::class.java.name,
                    PHYSICAL_KEY to SpringPhysicalNamingStrategy::class.java.name,
                    QUOTED_KEY to true,
                    LAZY_LOAD_KEY to true,
                    DIALECT_KEY to jpaProperties.dialect,
                    DDL_AUTO_KEY to jpaProperties.ddlAuto
                )
            )
            .build()
    }

    open fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}
