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
  
  def test = Action(parse.json) { request => 
   request.body.validate(gameRequest).map{ 
      case (direction, numbers) => 
        Ok(Json.obj("numbers" -> next(direction, numbers) ))  
    }.recoverTotal{
      e => BadRequest("Detected error:"+ JsError.toFlatJson(e))
    }
  }

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}