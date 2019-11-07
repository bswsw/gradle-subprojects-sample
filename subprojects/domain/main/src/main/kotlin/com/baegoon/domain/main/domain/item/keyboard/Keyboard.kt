package com.baegoon.domain.main.domain.item.keyboard

import com.baegoon.domain.main.domain.item.Item
import com.baegoon.domain.main.domain.item.ItemType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
@DiscriminatorValue(ItemType.Value.KEYBOARD)
class Keyboard(
    name: String,
    price: Int,
    val keys: Int,
    @Enumerated(EnumType.STRING)
    val connectType: KeyboardConnectType
): Item(name, price)
