package com.lendable.cart

import com.lendable.model.Item

interface ShoppingCart {
    fun addItem(item: Item, quantity: Int = 1)
    fun removeItem(item: Item, quantity: Int = 1)
    fun applyTwoForOneDiscount()
    fun generateReceipt(): String
}
