package controllers

import play.api._
import play.api.mvc._
import game.Game._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.data._
import play.api.data.Forms._
import models.Task

object Application extends Controller {

  val taskForm = Form(
    "label" -> nonEmptyText
  )

//  val Home = Redirect(routes.Application.index2(new List(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0), 1))
  
  def index2 = Action { Ok(views.html.index2(List(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0), 1)) } 
  
  def dmove = Action {
//    Ok(routes.Application.tasks)
    Ok("Hello World")
  }
  
  def tasks = TODO
//    Action {
////    Ok(views.html.index2(Task.all(), taskForm))
//  }
  
  
  def newTask = TODO
  
  def deleteTask(id: Long) = TODO
  
//  views.html.index("Your new application is ready.")
  
  val gameRequest: Reads[(String, List[Int], Int)] = {
    (JsPath \ "direction").read[String] and
    (JsPath \ "numbers").read[List[Int]] and
    (JsPath \ "mode").read[Int]
  }.tupled
  
  def move = Action(parse.json) { request => 
   request.body.validate(gameRequest).map{ 
      case (direction, numbers, mode) => {
    	  val nextNums = next(direction, numbers, mode)
    	  Ok(Json.obj("numbers" -> nextNums._1, "score" -> nextNums._2 ))  
      }
    }.recoverTotal{
      e => BadRequest("Detected error:"+ JsError.toFlatJson(e))
    }
  }

//  def index = Action {
//    Ok(views.html.index("Your new application is ready."))
//  }
  
}