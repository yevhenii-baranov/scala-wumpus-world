package ua.nure.baranov.wumpus

import akka.actor.typed.Behavior

class Speleologist(navigator: Navigator, environment: Environment) {

  def speleologistEnvActor: Behavior[Environment.Response] = ???

  def speleologistNavActor: Behavior[Navigator.ActionResponse] = ???
}
