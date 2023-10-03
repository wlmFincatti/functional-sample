package playground

object PatternMatching extends App {

  class Person(val name: String, val age: Int)

  private object Person {
    def unapply(person: Person): Option[(String, Int)] =
      if (person.name.contains("a")) Some(person.name, person.age) else None
  }

  private val person = new Person("William", 21)

  person match {
    case Person(name, age) => println(s"Name $name and age $age")
    case _                 => println("Don't have 'a' in name")
  }

}
