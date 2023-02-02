class Soda(_name: String, _price: Int, _num: Int) {
    var name: String
    var price: Int
    var num: Int

    init {
        name = _name
        price = _price
        num = _num
    }

    fun order() {
        if (num > 0) num.dec()
        println("$name = $num")
    }

}
