package com.baegoon.domain.main.domain.item.mouse

import com.baegoon.domain.main.domain.item.Item
import com.baegoon.domain.main.domain.item.ItemType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue(ItemType.Value.MOUSE)
class Mouse(
    name: String,
    price: Int,
    val buttons: Int,
    val weight: Int
): Item(name, price)
