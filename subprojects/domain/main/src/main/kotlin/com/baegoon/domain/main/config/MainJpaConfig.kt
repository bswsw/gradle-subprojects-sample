package com.baegoon.domain.main.config

import com.baegoon.domain.common.config.BaseJpaConfig
import com.baegoon.domain.common.properties.JpaProperties
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@EnableJpaRepositories(
    basePackages = [MainJpaConfig.REPOSITORY_PACKAGE_NAME],
    entityManagerFactoryRef = MainJpaConfig.ENTITY_MANAGER_FACTORY_BEAN_NAME,
    transactionManagerRef = MainJpaConfig.TRANSACTION_MANAGER_BEAN_NAME
)
open class MainJpaConfig : BaseJpaConfig() {

    companion object {
        private const val CONFIG_NAME = "main"

        private const val JPA_PROPERTIES = "$CONFIG_NAME-jpa"
        private const val JPA_PROPERTIES_BEAN = "${CONFIG_NAME}JpaProperties"
        private const val DATA_SOURCE_PROPERTIES = "$JPA_PROPERTIES.datasource"
        private const val DATA_SOURCE_PROPERTIES_BEAN = "${CONFIG_NAME}DataSourceProperties"
        private const val DATA_SOURCE_BEAN_NAME = "${CONFIG_NAME}DataSource"
        const val REPOSITORY_PACKAGE_NAME = "com.baegoon.domain.$CONFIG_NAME"
        const val ENTITY_MANAGER_FACTORY_BEAN_NAME = "${CONFIG_NAME}EntityManagerFactory"
        const val TRANSACTION_MANAGER_BEAN_NAME = "${CONFIG_NAME}TransactionManager"
    }

    @Primary
    @Bean(JPA_PROPERTIES_BEAN)
    @ConfigurationProperties(JPA_PROPERTIES)
    override fun jpaProperties(): JpaProperties {
        return super.jpaProperties()
    }

    @Primary
    @Bean(DATA_SOURCE_PROPERTIES_BEAN)
    @ConfigurationProperties(DATA_SOURCE_PROPERTIES)
    override fun dataSourceProperties(): DataSourceProperties {
        return super.dataSourceProperties()
    }

    @Primary
    @Bean(DATA_SOURCE_BEAN_NAME)
    override fun dataSource(
        @Qualifier(DATA_SOURCE_PROPERTIES_BEAN)
        dataSourceProperties: DataSourceProperties
    ): DataSource {
        return super.dataSource(dataSourceProperties)
    }

    @Primary
    @Bean(ENTITY_MANAGER_FACTORY_BEAN_NAME)
    override fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier(DATA_SOURCE_BEAN_NAME) dataSource: DataSource,
        @Qualifier(JPA_PROPERTIES_BEAN) jpaProperties: JpaProperties
    ): LocalContainerEntityManagerFactoryBean {
        return super.entityManagerFactory(builder, dataSource, jpaProperties)
    }

    @Primary
    @Bean(TRANSACTION_MANAGER_BEAN_NAME)
    override fun transactionManager(
        @Qualifier(ENTITY_MANAGER_FACTORY_BEAN_NAME)
        entityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return super.transactionManager(entityManagerFactory)
    }
}
