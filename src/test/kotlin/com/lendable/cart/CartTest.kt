package com.lendable.cart

import com.lendable.model.Item
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.maps.shouldNotContainKey
import io.kotest.matchers.shouldBe

class CartTest : StringSpec({

    lateinit var cart: Cart
    lateinit var item: Item

    beforeTest {
        cart = Cart()
        item = Item("1", "Cornflakes", 2.50)
    }

    "should add items to the cart" {
        cart.addItem(item, 3)
        cart.getItems()[item] shouldBe 3
    }

    "should remove items from the cart" {
        cart.addItem(item, 3)
        cart.removeItem(item, 2)
        cart.getItems()[item] shouldBe 1
    }

    "should remove item completely when quantity is zero" {
        cart.addItem(item, 1)
        cart.removeItem(item, 1)
        cart.getItems() shouldNotContainKey item
    }

    "should apply 2-for-1 discount correctly" {
        cart.addItem(item, 5)
        cart.applyTwoForOneDiscount()
        cart.getItems()[item] shouldBe 3
    }

    "should generate correct receipt" {
        val item2 = Item("2", "Milk", 1.75)
        cart.addItem(item, 3)
        cart.addItem(item2, 1)
        val receipt = cart.generateReceipt()
        val expectedReceipt = """
        Receipt:
        Cornflakes           x3    $7.50
        Milk                 x1    $1.75
        Total: $9.25
    """.trimIndent()
        receipt shouldBe expectedReceipt
    }

    "should not apply 2-for-1 discount if quantity is less than 2" {
        cart.addItem(item, 1)
        cart.applyTwoForOneDiscount()
        cart.getItems()[item] shouldBe 1
    }

    "should apply 2-for-1 discount correctly when quantity is exactly 2" {
        cart.addItem(item, 2)
        cart.applyTwoForOneDiscount()
        cart.getItems()[item] shouldBe 1
    }

    "should generate correct receipt for empty cart" {
        val receipt = cart.generateReceipt()
        val expectedReceipt = """
        Receipt:
        Total: $0.00
    """.trimIndent()
        receipt shouldBe expectedReceipt
    }

    "should handle removing item from empty cart gracefully" {
        cart.removeItem(item, 1)
        cart.getItems() shouldNotContainKey item
    }

    "should throw exception when removing more items than present" {
        cart.addItem(item, 1)
        shouldThrow<IllegalArgumentException> {
            cart.removeItem(item, 2)
        }.message shouldBe "Cannot remove more items than are present in the cart"
    }
})
