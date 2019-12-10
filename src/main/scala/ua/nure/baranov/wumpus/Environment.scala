package ua.nure.baranov.wumpus

import akka.actor.Actor

class Environment(layout: String) extends Actor {

  private val wumpusPositions: RoomPosition = parseWumpusPosition(layout)
  private val goldPosition: RoomPosition = parseGoldPosition(layout)
  private var isGoldTaken: Boolean = false
  private var isWumpusKilled: Boolean = false
  private val roomSize: (Int, Int) = parseRoomSize(layout)
  private var speleologistPosition: RoomPosition = RoomPosition(0, 0)
  private var speleologistDirection: Direction = Right


  override def receive: Receive = ???


  def parseWumpusPosition(layout: String): RoomPosition = {
    val rows = layout.split("\n")
    val wumpusIndexes = rows.map(_.indexOf('W'))
    xxwumpusIndexes.max
  }
  def parseRoomSize(layout: String): (Int, Int) = {
    val rows = layout.split("\n")
    val height = rows.length
    //val width = rows[0].
  }
  def parseGoldPosition(layout: String): RoomPosition = ???
}

object Environment {
  case class EnvironmentRequest()
  case class EnvironmentResponse(percept: WumpusPercept)
  case class Action(action: SpeleologistAction)
  case class ActionResponse(actionResult: ActionResult)
}

case class RoomPosition(x: Int, y: Int)
