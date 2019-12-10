package ua.nure.baranov.wumpus

import akka.actor.typed.receptionist.Receptionist
import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import ua.nure.baranov.wumpus.Environment.{ActionResponse, EnvironmentResponse, Request}

class Environment(layout: String) {

//  val actor =

  def performAction(action: SpeleologistAction): ActionResult = ???

  def composeCurrentState() = ???

  val envBehavior: Behavior[Request] = Behaviors.receive((context, message) => {

    var killSwitch: Boolean = false

    message match {
      case Environment.EnvironmentRequest(sender) => {
        val environmentState = composeCurrentState()
        sender ! EnvironmentResponse(environmentState)
        killSwitch = false
      }

      case Environment.Action(action, sender) => {
        val result = performAction(action)

        sender ! ActionResponse(result)
        killSwitch = if (result == KeepGoing) false else true
      }
    }

    if (killSwitch) Behaviors.stopped else Behaviors.same
  })


  private val wumpusPositions: RoomPosition = parseWumpusPosition(layout)
  private val goldPosition: RoomPosition = parseGoldPosition(layout)
  private val pitPosition: RoomPosition = parsePitPosition(layout)
  private var isGoldTaken: Boolean = false
  private var isWumpusKilled: Boolean = false
  private val roomSize: (Int, Int) = parseRoomSize(layout)
  private var speleologistPosition: RoomPosition = RoomPosition(0, 0)
  private var speleologistDirection: Direction = Right

  def getSymbolCoordinates(layout: String, symbol: Char): RoomPosition = {
    val rows = layout.split("\r\n")
    val symbolIndexes = rows.map(_.indexOf(symbol))

    val roomPosition = symbolIndexes.zipWithIndex.maxBy(_._1)
    RoomPosition(roomPosition._1, roomPosition._2)
  }

  def parseWumpusPosition(layout: String): RoomPosition = {
    val symbol = 'W'

    getSymbolCoordinates(layout, symbol)
  }

  def parsePitPosition(layout: String): RoomPosition = {
    val symbol = 'P'

    getSymbolCoordinates(layout, symbol)
  }

  def parseRoomSize(layout: String): (Int, Int) = {
    val rows = layout.split("\r\n")
    val height = rows.length
    val width = rows(0).length
    (height, width)
  }

  def parseGoldPosition(layout: String): RoomPosition = {
    val symbol = 'G'

    getSymbolCoordinates(layout, symbol)
  }
}

object Environment {
  sealed trait Request
  sealed trait Response

  case class EnvironmentRequest(sender: ActorRef[Response]) extends Request
  case class EnvironmentResponse(percept: WumpusPercept) extends Response
  case class Action(action: SpeleologistAction, sender: ActorRef[Response]) extends Request
  case class ActionResponse(actionResult: ActionResult) extends Response
}

case class RoomPosition(x: Int, y: Int)
