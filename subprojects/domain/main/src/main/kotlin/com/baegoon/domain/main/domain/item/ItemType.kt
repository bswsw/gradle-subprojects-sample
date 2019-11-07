package com.baegoon.domain.main.domain.item

enum class ItemType(val value: String) {
    LAPTOP(Value.LAPTOP),
    KEYBOARD(Value.KEYBOARD),
    MOUSE(Value.MOUSE);

    object Value {
        const val LAPTOP = "LAPTOP"
        const val KEYBOARD = "KEYBOARD"
        const val MOUSE = "MOUSE"
    }
}
