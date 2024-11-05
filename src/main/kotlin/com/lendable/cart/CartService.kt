package com.lendable.cart

import com.lendable.model.Item

/**
 * A shopping cart that allows adding, removing items, applying discounts, and generating receipts.
 */
class CartService : ShoppingCart {

    private val items = mutableMapOf<Item, Int>()

    /**
     * Adds an item to the cart with the specified quantity.
     *
     * @param item The item to add.
     * @param quantity The quantity of the item to add.
     */
    override fun addItem(item: Item, quantity: Int) {
        items[item] = items.getOrDefault(item, 0) + quantity
    }

    /**
     * Removes the specified quantity of an item from the cart.
     * If the quantity to remove is greater than or equal to the current quantity, the item is removed completely.
     * Throws an IllegalArgumentException if the item is not found in the cart or if the quantity to remove is greater than the current quantity.
     *
     * @param item The item to remove.
     * @param quantity The quantity of the item to remove.
     * @throws IllegalArgumentException if the item is not found in the cart or if the quantity to remove is greater than the current quantity.
     */
    override fun removeItem(item: Item, quantity: Int) {
        val currentQuantity = items[item] ?: return // Gracefully handle if item is not in the cart
        if (quantity > currentQuantity) {
            throw IllegalArgumentException("Cannot remove more items than are present in the cart")
        }
        if (quantity == currentQuantity) {
            items.remove(item)
        } else {
            items[item] = currentQuantity - quantity
        }
    }

    /**
     * Applies a two-for-one discount to all items in the cart.
     */
    override fun applyTwoForOneDiscount() {
        items.forEach { (item, quantity) ->
            items[item] = quantity / 2 + quantity % 2
        }
    }

    /**
     * Generates a receipt for the items in the cart.
     *
     * @return A string representation of the receipt.
     */
    override fun generateReceipt(): String {
        val receipt = StringBuilder("Receipt:\n")
        var total = 0.0

        items.forEach { (item, quantity) ->
            val itemTotal = item.price * quantity
            receipt.append(String.format("%-20s x%-4d \$%.2f\n", item.name, quantity, itemTotal))
            total += itemTotal
        }

        receipt.append(String.format("Total: \$%.2f", total))
        return receipt.toString()
    }

    /**
     * Returns the items in the cart.
     *
     * @return A map of items and their quantities.
     */
    fun getItems(): Map<Item, Int> {
        return items
    }
}