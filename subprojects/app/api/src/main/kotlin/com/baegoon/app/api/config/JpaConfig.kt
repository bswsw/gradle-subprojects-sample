package com.baegoon.app.api.config

import com.baegoon.domain.core.config.ChainedTransactionManagerConfig
import com.baegoon.domain.main.config.MainJpaConfig
import com.baegoon.domain.sub.config.SubJpaConfig
import org.springframework.context.annotation.Configuration

@Configuration
class MainJpaConfig : MainJpaConfig()

@Configuration
class SubJpaConfig : SubJpaConfig()

@Configuration
class ChainedTransactionManagerConfig : ChainedTransactionManagerConfig()
