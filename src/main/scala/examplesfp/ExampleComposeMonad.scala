package examplesfp

object ExampleComposeMonad extends App {

  private case class User(name: String, age: Int, address: Option[Address])

  private case class Address(street: String, number: Int, complement: Option[String], coordinates: Option[Coordinates])

  private case class Coordinates(latLong: Option[(Double, Double)])

  private val user =
    User("William", 35, Some(Address("Rua 1", 10, Some("Casa 1"), Some(Coordinates(Some(-23.5505, -46.6333))))))

  private def imperativeMethod(): Unit = {
    if (user.address.isDefined) {
      if (user.address.get.coordinates.isDefined) {
        if (user.address.get.coordinates.get.latLong.isDefined) {
          println(
            s"User live in coordinates latitude: ${user.address.get.coordinates.get.latLong.get._1}, longitude: ${user.address.get.coordinates.get.latLong.get._2}"
          )
        }
      }
    }
  }

  private def functionalMethod(): Unit = {
    user.address
      .flatMap(_.coordinates)
      .flatMap(_.latLong)
      .map(latLong => s"User live in coordinates latitude: ${latLong._1}, longitude: ${latLong._2}")
      .fold(println("User don't have coordinates"))(println)
  }

  private def functionalWithForComprehensionMethod(): Unit = {
    for {
      address     <- user.address
      coordinates <- address.coordinates
      latLong     <- coordinates.latLong
    } yield {
      println(s"User live in coordinates latitude: ${latLong._1}, longitude: ${latLong._2}")
    }
  }

  imperativeMethod()
  functionalMethod()
  functionalWithForComprehensionMethod()
}
