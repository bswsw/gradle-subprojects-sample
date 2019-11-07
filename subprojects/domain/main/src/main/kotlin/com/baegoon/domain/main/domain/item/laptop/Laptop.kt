package com.baegoon.domain.main.domain.item.laptop

import com.baegoon.domain.main.domain.item.Item
import com.baegoon.domain.main.domain.item.ItemType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue(ItemType.Value.LAPTOP)
class Laptop(
    name: String,
    price: Int,
    val screenSize: Int,
    val ports: Int
) : Item(name, price)
