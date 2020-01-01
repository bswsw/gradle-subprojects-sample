package com.baegoon.domain.core.config

import com.baegoon.domain.main.config.MainJpaConfig
import com.baegoon.domain.sub.config.SubJpaConfig
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.transaction.ChainedTransactionManager
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class ChainedTransactionManagerConfig {

    companion object {
        const val TRANSACTION_MANAGER_BEAN_NAME = "chainedTransactionManager"
    }

    @Bean
    fun chainedTransactionManager(
        @Qualifier(MainJpaConfig.TRANSACTION_MANAGER_BEAN_NAME)
        mainTransactionManager: PlatformTransactionManager,
        @Qualifier(SubJpaConfig.TRANSACTION_MANAGER_BEAN_NAME)
        subTransactionManager: PlatformTransactionManager
    ): ChainedTransactionManager {
        return ChainedTransactionManager(
            mainTransactionManager,
            subTransactionManager
        )
    }
}
