package com.baegoon.domain.main.config

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.reflect.KClass

abstract class MainQuerydslRepositorySupport(domainClass: KClass<*>) : QuerydslRepositorySupport(domainClass.java) {

    @PersistenceContext(unitName = MainJpaConfig.PERSISTENCE_UNIT_NAME)
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }
}
