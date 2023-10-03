package playground

import scala.util.Random

object Combine extends App {

  private val colors: Seq[String]   = List("white", "black")
  private val letters: List[String] = List("a", "b", "c", "d")
  private val numbers: List[Int]    = List(1, 2, 3, 4)

  private val value: List[String] = letters.flatMap(l => numbers.flatMap(n => colors.map(c => s"$l - $n - $c")))

  println(value)

  println(for {
    l <- letters
    n <- numbers if n >= 1
    c <- colors
  } yield s"$l - $n - $c")

  case class Connection(host: String, port: String) {
    val connect = s"Connected, port $port, host $host"
  }

  private object Connection {
    private val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean) Some(new Connection(host, port)) else None
  }

  private val config: Map[String, String] = Map("host" -> "localhost", "port" -> "8080")

  println(for {
    h <- config.get("host")
    p <- config.get("port")
    c <- Connection(h, p)
  } yield c.connect)

}
