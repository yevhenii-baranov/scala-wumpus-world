package ua.nure.baranov.wumpus

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

object Main {

  val layout: String =
    """****
      |*P**
      |**G*
      |*W**""".stripMargin

  def main(args: Array[String]): Unit = {
    val environment = new Environment(layout)
    val navigator = new Navigator
    val speleologist = new Speleologist


    val system: ActorSystem[Nothing] = ActorSystem(Behaviors.setup(context => {
      val envRef = context.spawn(environment.envBehavior, "environment")
      val navRef = context.spawn(navigator.navigatorActor, "navigator")
      val spelRef = context.spawn(speleologist.setupActor(navRef, envRef), "speleologist")
      Behaviors.same
    }), "system")
  }
}
