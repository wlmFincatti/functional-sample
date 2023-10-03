package examplesfp

object ExampleComposeMonad extends App {

  private case class User(name: String, age: Int, address: Option[Address])

  private case class Address(street: String, number: Int, complement: Option[String], coordinates: Option[Coordinates])

  private case class Coordinates(latLong: Option[(Double, Double)])

  private val userWithCoordinates =
    User("William", 35, Some(Address("Rua 1", 10, Some("Casa 1"), Some(Coordinates(Some(-23.5505, -46.6333))))))

  private val userWithoutCoordinates =
    User("William", 35, Some(Address("Rua 1", 10, Some("Casa 1"), None)))

  private def imperativeMethod(user: User): Unit = {
    if (user.address.isDefined) {
      if (user.address.get.coordinates.isDefined) {
        if (user.address.get.coordinates.get.latLong.isDefined) {
          println(
            s"User live in coordinates latitude: ${user.address.get.coordinates.get.latLong.get._1}, longitude: ${user.address.get.coordinates.get.latLong.get._2}"
          )
        } else {
          println("The user does not have valid coordinates")
        }
      } else {
        println("The user does not have valid coordinates")
      }
    }
  }

  private def functionalMethod(user: User): Unit = {
    user.address
      .flatMap(_.coordinates)
      .flatMap(_.latLong)
      .map(latLong => s"User live in coordinates latitude: ${latLong._1}, longitude: ${latLong._2}")
      .fold(println("The user does not have valid coordinates"))(println)
  }

  private def functionalWithForComprehensionMethod(user: User): Unit = {
    (for {
      address     <- user.address
      coordinates <- address.coordinates
      latLong     <- coordinates.latLong
    } yield {
      println(s"User live in coordinates latitude: ${latLong._1}, longitude: ${latLong._2}")
    }) getOrElse {
      println("The user does not have valid coordinates")
    }
  }

  imperativeMethod(userWithCoordinates)
  functionalMethod(userWithCoordinates)
  functionalWithForComprehensionMethod(userWithCoordinates)
}
