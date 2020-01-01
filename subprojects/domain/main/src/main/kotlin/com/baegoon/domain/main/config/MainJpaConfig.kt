package com.baegoon.domain.main.config

import com.baegoon.domain.common.config.BaseJpaConfig
import com.baegoon.domain.common.properties.JpaProperties
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = [MainJpaConfig.REPOSITORY_PACKAGE_NAME],
    entityManagerFactoryRef = MainJpaConfig.ENTITY_MANAGER_FACTORY_BEAN_NAME,
    transactionManagerRef = MainJpaConfig.TRANSACTION_MANAGER_BEAN_NAME
)
class MainJpaConfig : BaseJpaConfig() {

    companion object {
        private const val UNIT_NAME = "main" // custom

        private const val JPA_PROPERTIES = "$UNIT_NAME$JPA_PROPERTIES_SUFFIX"
        private const val DATA_SOURCE_PROPERTIES = "$JPA_PROPERTIES$DATA_SOURCE_PROPERTIES_SUFFIX"
        private const val JPA_PROPERTIES_BEAN = "$UNIT_NAME$JPA_PROPERTIES_BEAN_SUFFIX"
        private const val DATA_SOURCE_PROPERTIES_BEAN = "$UNIT_NAME$DATA_SOURCE_PROPERTIES_BEAN_SUFFIX"
        private const val DATA_SOURCE_BEAN_NAME = "$UNIT_NAME$DATA_SOURCE_BEAN_NAME_SUFFIX"
        const val REPOSITORY_PACKAGE_NAME = "$REPOSITORY_PACKAGE_NAME_PREFIX$UNIT_NAME"
        const val ENTITY_MANAGER_FACTORY_BEAN_NAME = "$UNIT_NAME$ENTITY_MANAGER_FACTORY_BEAN_NAME_SUFFIX"
        const val TRANSACTION_MANAGER_BEAN_NAME = "$UNIT_NAME$TRANSACTION_MANAGER_BEAN_NAME_SUFFIX"
        const val PERSISTENCE_UNIT_NAME = "$UNIT_NAME$PERSISTENCE_UNIT_NAME_SUFFIX"
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

    override fun persistenceUnit(): String {
        return PERSISTENCE_UNIT_NAME
    }

    override fun entityPackage(): String {
        return REPOSITORY_PACKAGE_NAME
    }
}
