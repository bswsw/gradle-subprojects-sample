package com.baegoon.domain.main.domain.item

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseItemRepository<T : Item> : JpaRepository<T, Long>
