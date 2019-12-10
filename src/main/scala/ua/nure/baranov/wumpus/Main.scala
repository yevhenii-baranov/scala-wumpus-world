package ua.nure.baranov.wumpus

import akka.actor.{Actor, ActorSystem}

object Main {

  val layout: String =
    """****
      |*P**
      |**G*
      |*W**""".stripMargin

  def main(args: Array[String]): Unit = {
    val system: ActorSystem = ActorSystem("wumpus-world")

    val environment = new Environment(layout)
    val navigator = new Navigator
    val speleologist = new Speleologist(navigator, environment)
  }
}
