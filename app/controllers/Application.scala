package controllers

import play.api._
import play.api.mvc._
import game.Game._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object Application extends Controller {

  val gameRequest: Reads[(String, List[Int])] = {
    (JsPath \ "direction").read[String] and
    (JsPath \ "numbers").read[List[Int]]
  }.tupled
  
  def move = Action(parse.json) { request => 
   request.body.validate(gameRequest).map{ 
      case (direction, numbers) => {
    	  val nextNums = next(direction, numbers)
    	  Ok(Json.obj("numbers" -> nextNums._1, "score" -> nextNums._2 ))  
      }
    }.recoverTotal{
      e => BadRequest("Detected error:"+ JsError.toFlatJson(e))
    }
  }

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
}