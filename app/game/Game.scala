package game


import scala.util.Random

object Game {

  val random = new Random
    
  def printNums(l: List[Int]) = for (x <- l.grouped(4).toList) {
    println(x mkString ",\t")
  }

  private def up(nums: List[Int]): List[List[Int]] = (for {
    x <- 0 to 3
    y <- x to 15 by 4
  } yield (nums(y))).toList.reverse.grouped(4).toList.reverse

  private def down(nums: List[Int]): List[List[Int]] = (for {
    x <- 0 to 3
    y <- x to 15 by 4
  } yield (nums(y))).toList.grouped(4).toList

  private def left(nums: List[Int]): List[List[Int]] = nums.reverse.grouped(4).toList.reverse

  private def right(nums: List[Int]): List[List[Int]] = nums.grouped(4).toList

  def calc(nums: List[Int]): List[Int] = {
    def calcAcc(nextNums: List[Int], numsAcc: List[Int]): List[Int] = nextNums match {
      case List() => numsAcc.reverse.padTo(4, 0).reverse
      case x :: xs =>
        if (xs.isEmpty || x != xs.head) calcAcc(xs, x :: numsAcc)
        else calcAcc(xs.tail, (x + xs.head) :: numsAcc)
    }
    calcAcc(nums.filter(p => p != 0).reverse, List())
  }

  //TODO: generalize this pattern matching
  def move(direction: String, nums: List[Int]): List[Int] = {
    val sum = (for {
      x <- direction match {
        case "up" => up(nums)
        case "down" => down(nums)
        case "left" => left(nums)
        case "right" => right(nums)
      }
    } yield (calc(x))).flatten

    direction match {
      case "up" => up(sum).flatten.reverse
      case "down" => down(sum).flatten
      case "right" => right(sum).flatten
      case "left" => left(sum).flatten
    }
  }
  
  def insertRandom(nums: List[Int]): List[Int] = {
    val zeros = nums.zipWithIndex.filter(_._1 == 0).map(_._2)
    
    if (zeros.nonEmpty) nums updated (zeros(random.nextInt(zeros.length - 1)), 2)
    else nums
  }
  
  def next(direction: String, nums: List[Int]) = insertRandom(move(direction, nums))
  
}