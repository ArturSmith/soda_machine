enum class SodaMachineCommands(val commandValue: String) {
    INSERT_COMMAND("insert"),
    INSERTED_MONEY_COMMAND("inserted money"),
    ORDER_COMMAND("order"),
    SMS_ORDER_COMMAND("sms order"),
    RECALL_COMMAND("recall")
}