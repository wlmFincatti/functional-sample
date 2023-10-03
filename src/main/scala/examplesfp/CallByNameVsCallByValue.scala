package examplesfp

object CallByNameVsCallByValue extends App {

  private def funcByValue(n: Long): Unit   = {
    println(n)
    println(n)
  }
  private def funcByName(n: => Long): Unit = {
    println(n)
    println(n)
  }

  funcByValue(System.nanoTime())
  funcByName(System.nanoTime())

}
