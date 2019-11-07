package com.baegoon.domain.sub.config

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.reflect.KClass

abstract class SubQuerydslRepositorySupport(domainClass: KClass<*>) : QuerydslRepositorySupport(domainClass.java) {

    @PersistenceContext(unitName = SubJpaConfig.PERSISTENCE_UNIT_NAME)
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }
}
