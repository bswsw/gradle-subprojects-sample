package com.baegoon.domain.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("multiple-datasources")
class MultipleDataSourceProperties {

    val default = DataSourceProperties()

    class DataSourceProperties {
        lateinit var jdbcUrl: String
        lateinit var username: String
        lateinit var password: String
        lateinit var driverClassName: String
        lateinit var dialect: String
        lateinit var entityPackage: String
        lateinit var persistenceUnit: String
        lateinit var ddlAuto: String
    }
}
