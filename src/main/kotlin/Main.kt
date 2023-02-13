import Soda.*
import SodaMachineCommands.*

fun main() {


    sodaMachine()
}

fun sodaMachine() {
    printAvailableCommands()
    var money: Int = 0
    var inventory: List<Soda> = listOf(
        Soda("Coke", 25, 3),
        Soda("Sprite", 15, 3),
        Soda("Fanta", 30, 3)
    )

    while (true) {

        when (readln()) {
            INSERT_COMMAND.commandValue -> money += insertMoney()
            INSERTED_MONEY_COMMAND.commandValue -> println("You have $money money")
            ORDER_COMMAND.commandValue -> {
                val order = orderSoda(money, inventory)
                if (order.isNotEmpty()) {
                    inventory = order
                    money = 0
                }
            }

            SMS_ORDER_COMMAND.commandValue -> {
                println(
                    "Order by sms\n" +
                            "Please"
                )
                val order = orderSoda(money, inventory)
                if (order.isNotEmpty()) {
                    inventory = order
                    money = 0
                }
            }

            RECALL_COMMAND.commandValue -> {
                println("Returning $money to customer")
                money = 0
            }
        }
    }
}


private fun insertMoney(): Int {
    var money: Int = 0
    println("Insert money please")
    val input = readlnOrNull()
    money += input!!.toInt()
    return money
}

private fun orderSoda(money: Int, inventory: List<Soda>): List<Soda> {
    println("Choose soda: ")
    inventory.forEach { print("${it.name}\n") }
    val userSoda = readln()


    inventory.forEach { soda ->

        if (soda.name == userSoda) {
            if (soda.num > 0) {
                if (soda.price <= money) {
                    soda.order()
                    println("Giving ${soda.name} out")
                    println(("Giving ${money - soda.price} out in change"))
                    return inventory
                } else {
                    println("Not enough money")
                    return emptyList()
                }
            } else {
                println("No such soda")
                return emptyList()
            }
        }

    }
    return inventory
}


fun printAvailableCommands() {
    println(
        "Available commands:\n" +
                "                insert - Money put into money slot\n" +
                "                order (coke, sprite, fanta) - Order from machines buttons\n" +
                "                sms order (coke, sprite, fanta) - Order sent by sms\n" +
                "                recall - gives money back\n" +
                "                -------\n" +
                "                Inserted money: + _money\n"
    )
}