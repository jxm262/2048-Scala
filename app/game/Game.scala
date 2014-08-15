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

  def calc(nums: List[Int]): (List[Int], Int) = {
    def calcAcc(nextNums: List[Int], numsAcc: List[Int], scoreAcc: Int): (List[Int], Int) = nextNums match {
      case List() => (numsAcc.reverse.padTo(4, 0).reverse, scoreAcc)
      case x :: xs =>
        if (xs.isEmpty || x != xs.head) calcAcc(xs, x :: numsAcc, scoreAcc)
        else calcAcc(xs.tail, (x + xs.head) :: numsAcc, scoreAcc + (x + xs.head))
    }
    calcAcc(nums.filter(p => p != 0).reverse, List(), 0)
  }

  //TODO: generalize this pattern matching
  def move(direction: String, nums: List[Int]): (List[Int], Int) = {
    val sum = (for {
      x <- direction match {
        case "up" => up(nums)
        case "down" => down(nums)
        case "left" => left(nums)
        case "right" => right(nums)
      }
    } yield (calc(x))).unzip

    val m = (sum._1.flatten, sum._2.sum)

    direction match {
      case "up" => (up(sum._1.flatten).flatten.reverse, sum._2.sum)
      case "down" => (down(sum._1.flatten).flatten, sum._2.sum)
      case "right" => (right(sum._1.flatten).flatten, sum._2.sum)
      case "left" => (left(sum._1.flatten).flatten, sum._2.sum)
    }
  }

  def insertRandom(nums: List[Int], num: Int): List[Int] = {
    val zeros = nums.zipWithIndex.filter(_._1 == 0).map(_._2)

    if (zeros.nonEmpty) nums updated (zeros(random.nextInt(zeros.length)), num)
    else nums
  }

  def next(direction: String, nums: List[Int], mode: Int) = {
    def nextMove = {
      val nextNums = move(direction, nums)
      mode match {
        case 1 => (insertRandom(nextNums._1, 2), nextNums._2)
        case 2 =>
          if (nums != nextNums._1) (insertRandom(nextNums._1, 2), nextNums._2)
          else (nums, 0)
        case 3 =>
          if (nums.count(_ == 1024) != nextNums._1.count(_ == 1024) && nextNums._1.contains(1024))
            (insertRandom(nextNums._1, 99999), nextNums._2)
          else if (nums != nextNums._1) (insertRandom(nextNums._1, 2), nextNums._2)
          else (nums, 0)
      }
    }
    if (nums.sum == 0) (insertRandom(nums, 2), 0)
    else nextMove
  }

}