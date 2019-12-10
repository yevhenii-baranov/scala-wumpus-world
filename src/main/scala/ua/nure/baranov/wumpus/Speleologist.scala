package ua.nure.baranov.wumpus

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import ua.nure.baranov.wumpus.Environment.{ActionResponse, EnvironmentResponse}

class Speleologist {

  private var navRef: ActorRef[Navigator.ActionRequest] = ???
  private var envRef: ActorRef[Environment.Request] = ???


  def setupActor = Behaviors.setup(context => {
    // Find environment and navigator

    environmentBehavior
  })

  def environmentBehavior: Behavior[Main.Message] = Behaviors.receive[Environment.Response]((context, message) => {
    message match {
      case Environment.EnvironmentResponse(percept) =>
        navRef ! Navigator.ActionRequest(percept, "", context.self)

        navigatorBehavior
      case Environment.ActionResponse(actionResult: ActionResult) => ???
    }
  })

  def navigatorBehavior = Behaviors.receive[Navigator.ActionResponse] ((context, message) => {
      envRef ! Environment.PerformAction(message.action, context.self)

      environmentBehavior
    })

}
