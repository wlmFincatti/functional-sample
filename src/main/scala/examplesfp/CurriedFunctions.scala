package examplesfp

object CurriedFunctions extends App {

  private val simpleFunctionAdder = (x: Int, y: Int) => x + y

  private def simpleMethodAdder(x: Int, y: Int) = x + y

  private def curriedMethodAdder(x: Int)(y: Int) = x + y

  private val adder7SimpleFunc = curriedMethodAdder(7) _
  private val i: Int           = adder7SimpleFunc(7)
  println(i)

  private val intToInt: Int => Int = simpleFunctionAdder.curried(8)
  println(intToInt(9))

  private val intToInt1: Int => Int = simpleMethodAdder(9, _: Int)
  println(intToInt1(9))

  private val insertName = (a: String, b: String, c: String) => a + b + c

  private val stringToString: String => String = insertName("Hello, my name is ", _: String, " how are you?")
  println(stringToString("William"))

}
