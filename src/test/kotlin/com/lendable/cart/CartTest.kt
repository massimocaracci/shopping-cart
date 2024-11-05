package com.lendable.cart

import com.lendable.model.Item
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.maps.shouldNotContainKey
import io.kotest.matchers.shouldBe

class CartTest : StringSpec({

    lateinit var cartService: CartService
    lateinit var item: Item

    beforeTest {
        cartService = CartService()
        item = Item("1", "Cornflakes", 2.50)
    }

    "should add items to the cart" {
        cartService.addItem(item, 3)
        cartService.getItems()[item] shouldBe 3
    }

    "should remove items from the cart" {
        cartService.addItem(item, 3)
        cartService.removeItem(item, 2)
        cartService.getItems()[item] shouldBe 1
    }

    "should remove item completely when quantity is zero" {
        cartService.addItem(item, 1)
        cartService.removeItem(item, 1)
        cartService.getItems() shouldNotContainKey item
    }

    "should apply 2-for-1 discount correctly" {
        cartService.addItem(item, 5)
        cartService.applyTwoForOneDiscount()
        cartService.getItems()[item] shouldBe 3
    }

    "should generate correct receipt" {
        val item2 = Item("2", "Milk", 1.75)
        cartService.addItem(item, 3)
        cartService.addItem(item2, 1)
        val receipt = cartService.generateReceipt()
        val expectedReceipt = """
        Receipt:
        Cornflakes           x3    $7.50
        Milk                 x1    $1.75
        Total: $9.25
    """.trimIndent()
        receipt shouldBe expectedReceipt
    }

    "should not apply 2-for-1 discount if quantity is less than 2" {
        cartService.addItem(item, 1)
        cartService.applyTwoForOneDiscount()
        cartService.getItems()[item] shouldBe 1
    }

    "should apply 2-for-1 discount correctly when quantity is exactly 2" {
        cartService.addItem(item, 2)
        cartService.applyTwoForOneDiscount()
        cartService.getItems()[item] shouldBe 1
    }

    "should generate correct receipt for empty cart" {
        val receipt = cartService.generateReceipt()
        val expectedReceipt = """
        Receipt:
        Total: $0.00
    """.trimIndent()
        receipt shouldBe expectedReceipt
    }

    "should handle removing item from empty cart gracefully" {
        cartService.removeItem(item, 1)
        cartService.getItems() shouldNotContainKey item
    }

    "should throw exception when removing more items than present" {
        cartService.addItem(item, 1)
        shouldThrow<IllegalArgumentException> {
            cartService.removeItem(item, 2)
        }.message shouldBe "Cannot remove more items than are present in the cart"
    }
})
