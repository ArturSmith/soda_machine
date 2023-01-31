import Soda.*

fun main() {

    println(
        "Available commands:\n" +
                "                insert - Money put into money slot\n" +
                "                order (coke, sprite, fanta) - Order from machines buttons\n" +
                "                sms order (coke, sprite, fanta) - Order sent by sms\n" +
                "                recall - gives money back\n" +
                "                -------\n" +
                "                Inserted money: + _money\n"
    )

    sodaMachine()

}

fun sodaMachine() {

    // The started method for the machine
    var money: Int = 0
    var inventory: List<Soda> = listOf(Soda("Coke", 25, 3), Soda("Sprite", 15, 5), Soda("Fanta", 30, 6))

    while (true) {

        // Input action
        val input = readln()

        // Checking what was chosen
        when (input) {
            "insert" -> money += insertMoney()
            "inserted money" -> println("You have $money money")
            "order" -> {
                val order = order(money, inventory)
                if (order != null) {
                    inventory = order
                    money = 0
                }
            }

            "sms order" -> {
                println(
                    "Order by sms\n" +
                            "Please"
                )
                val order = order(money, inventory)
                if (order != null) {
                    inventory = order
                    money = 0
                }
            }

            "recall" -> {
                println("Returning $money to customer")
                money = 0
            }
        }
    }
}

fun insertMoney(): Int {
    var money: Int = 0
    println("Insert money please")
    val _input = readlnOrNull()
    money += _input!!.toInt()
    return money
}

fun order(_money: Int, _inventory: List<Soda>): List<Soda>? {
    var inventory: List<Soda>? = _inventory
    println("Choose soda: ")
    inventory?.forEach { print("${it.name}\n") }
    val soda = readLine()
    inventory?.forEach {
        if (it.name == soda) {
            if (it.num > 0) {
                if (_money >= it.price) {
                    println("Giving ${it.name} out")
                    println(("Giving ${_money - it.price} out in change"))
                    it.order()
                } else {
                    println("Not enough money. Need ${it.price - _money} more ")
                    inventory = null
                }
            } else {
                println("No such soda")
                inventory = null
            }
        }
    }
    return inventory
}
