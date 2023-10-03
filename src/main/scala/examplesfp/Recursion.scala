package examplesfp

import scala.annotation.tailrec

object Recursion extends App {

  private def factorial(n: Int): BigInt = {
    @tailrec
    def auxFactorial(n: Int, acomulador: BigInt): BigInt = {
      if (n <= 1) acomulador
      else {
        auxFactorial(n - 1, n * acomulador)
      }
    }

    auxFactorial(n, 1)
  }

  println(factorial(5))

  @tailrec
  private def concatString(string: String, n: Int, acomulador: String): String = {
    if (n <= 0) acomulador
    else concatString(string, n - 1, acomulador.concat("-").concat(string))
  }

  println(concatString("hello", 4, ""))

}
