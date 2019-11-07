package com.baegoon.domain.main.domain.item

import com.baegoon.domain.common.BaseEntity
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.Entity
import javax.persistence.Inheritance
import javax.persistence.InheritanceType

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "item_type")
abstract class Item(
    @Column(name = "item_name", nullable = false)
    var name: String,
    var price: Int
) : BaseEntity()
