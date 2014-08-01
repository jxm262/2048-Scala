package controllers

import play.api._
import play.api.mvc._
import game.Game._

object Application extends Controller {

  var nums = List(0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

  def reset() = nums = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
      
  def printNums(l: List[Int]) = ((for (x <- l.grouped(4).toList) yield (x mkString ",\t")) mkString "\n")
  def index = Action {
        Ok(views.html.index("Your new application is ready."))
	 
//    reset()
//    
//    val step0 = printNums(nums)
//    
//    nums = next("up", nums)
//    val step1 = printNums(nums)
//
//    nums = next("up", nums)
//    val step3 = printNums(nums)
//	
//    nums = next("up", nums)
//	val step5 = printNums(nums)
//    
//	nums = next("up", nums)
//	val step7 = printNums(nums)
//	
//	val res = step0 + "\n\n\n" + step1  + "\n\n\n" + step3 + "\n\n\n" + step5 + "\n\n\n" + step7
//	
//    Ok(res)
  }

}