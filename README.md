# Shopping Cart

This project is a simple digital shopping cart implementation in Kotlin. It allows adding and removing items, applying discounts, and generating receipts.

## Project Structure

- `src/main/kotlin/com/lendable/cart/Cart.kt`: Contains the `Cart` class implementation.
- `src/main/kotlin/com/lendable/cart/ShoppingCart.kt`: Contains the `ShoppingCart` interface.
- `src/main/kotlin/com/lendable/model/Item.kt`: Contains the `Item` data class.
- `src/test/kotlin/com/cart/lendable/CartTest.kt`: Contains unit tests for the `Cart` class.

## Features

- **Add Items**: Add items to the cart with a specified quantity.
- **Remove Items**: Remove a specified quantity of items from the cart.
- **Apply Discounts**: Apply a two-for-one discount to all items in the cart.
- **Generate Receipts**: Generate a receipt for the items in the cart.

## Getting Started

### Prerequisites

- Kotlin
- Gradle

### Building the Project

To build the project, run:

```sh
./gradlew build
```

### Running Tests

To run the tests, use:./gradlew test

```sh
./gradlew test
```

## Usage

To use the shopping cart, create a new instance of the `Cart` class and interact with it using the provided methods.

```kotlin   
val cart = Cart()
cart.add(Item("Apple", 0.60), 5)
cart.add(Item("Banana", 0.20), 10)
cart.remove(Item("Apple", 0.60), 2)
cart.applyDiscount()
val receipt = cart.generateReceipt()
println(receipt)
``` 

## Future Improvements

Here are some potential future improvements that could be made to enhance the functionality and usability of the shopping cart:

1. **Persistence**:
   - Add functionality to save and load the cart state to/from a file or database.

2. **Concurrency Handling**:
   - Ensure thread safety for operations on the cart, especially if the cart is to be used in a multi-threaded environment.

3. **User Interface**:
   - Develop a simple command-line interface (CLI) or graphical user interface (GUI) to interact with the shopping cart.

4. **API Integration**:
   - Create RESTful API endpoints to interact with the shopping cart, making it suitable for integration with other systems.

5. **Internationalization**:
   - Support multiple currencies and languages for a more global application.

6. **Unit and Integration Tests**:
   - Expand the test suite to include integration tests and more comprehensive unit tests.

## Contact

For questions or feedback, please contact [massimo.caracci@gmail.com].


