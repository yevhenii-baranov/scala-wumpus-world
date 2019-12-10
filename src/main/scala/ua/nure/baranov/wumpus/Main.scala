package ua.nure.baranov.wumpus

class Main {

  val layout: String =
    """
      | P
      |  G
      | W  """.stripMargin

  def main(args: Array[String]): Unit = {
    val environment = new Environment(layout)
    val navigator = new Navigator
    val speleologist = new Speleologist(navigator, environment)
  }
}
