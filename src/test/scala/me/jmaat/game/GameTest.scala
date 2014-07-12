package me.jmaat.game

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameTest extends FunSuite {
  import Game._

  trait testLists {
    val nums1 = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
    val nums2 = List(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
    val nums3 = List(2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3)
    val nums4 = List(0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0)
    val nums5 = List(1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1)
    val nums6 = List(1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1)
    val nums7 = List(0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0)

    val numsd = List(3, 1, 1, 1, 3, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2)
  }

  /*
   	 3, 1, 1, 1  to  6, 2, 2, 2
	 3, 1, 1, 1		 4, 4, 4, 4
	 2, 2, 2, 2		 0, 0, 0, 0
	 2, 2, 2, 2		 0, 0, 0, 0
   */
  test("move up, all cells calculate") {
    val nums = List(3, 1, 1, 1, 3, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2)
    assert(move("up", nums) === List(6, 2, 2, 2, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0))
  }

  /*
   	 3, 1, 1, 1  to  6, 2, 2, 2
	 3, 1, 1, 1		 0, 0, 0, 0
	 0, 0, 0, 0		 0, 0, 0, 0
	 0, 0, 0, 0		 0, 0, 0, 0
   */
  test("move up, top 2 rows only calculate") {
    val nums = List(3, 1, 1, 1, 3, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0)
    assert(move("up", nums) === List(6, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
  }

  /*
   	 0, 0, 0, 0  to  6, 2, 2, 2
	 0, 0, 0, 0		 0, 0, 0, 0
	 3, 1, 1, 1		 0, 0, 0, 0
	 3, 1, 1, 1		 0, 0, 0, 0
   */
  test("move up, bottom 2 rows only calculate") {
    val nums = List(0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 3, 1, 1, 1)
    assert(move("up", nums) === List(6, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
  }

  /*
   	 3, 1, 1, 1  to  6, 2, 2, 2
	 0, 0, 0, 0		 0, 0, 0, 0
	 0, 0, 0, 0		 0, 0, 0, 0
	 3, 1, 1, 1		 0, 0, 0, 0
   */
  test("move up, top and bottom rows only calculate") {
    val nums = List(3, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1)
    assert(move("up", nums) === List(6, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
  }

  /*
   	 0, 0, 0, 0  to  6, 2, 2, 2
	 3, 1, 1, 1		 0, 0, 0, 0
	 3, 1, 1, 1		 0, 0, 0, 0
	 0, 0, 0, 0		 0, 0, 0, 0
   */
  test("move up, middle 2 rows only calculate") {
    val nums = List(0, 0, 0, 0, 3, 1, 1, 1, 3, 1, 1, 1, 0, 0, 0, 0)
    assert(move("up", nums) === List(6, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
  }

  /*
   	 3, 1, 1, 1  to  0, 0, 0, 0
	 3, 1, 1, 1		 0, 0, 0, 0
	 2, 2, 2, 2		 6, 2, 2, 2
	 2, 2, 2, 2		 4, 4, 4, 4
   */
  test("move down, all cells calculate") {
    val nums = List(3, 1, 1, 1, 3, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2)
    assert(move("down", nums) === List(0, 0, 0, 0, 0, 0, 0, 0, 6, 2, 2, 2, 4, 4, 4, 4))
  }

  /*
   	 3, 1, 1, 1  to  0, 0, 0, 0
	 3, 1, 1, 1		 0, 0, 0, 0
	 0, 0, 0, 0		 0, 0, 0, 0
	 0, 0, 0, 0		 6, 2, 2, 2
   */
  test("move down, top 2 rows only calculate") {
    val nums = List(3, 1, 1, 1, 3, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0)
    assert(move("down", nums) === List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 2, 2, 2))
  }

  /*
   	 0, 0, 0, 0  to  0, 0, 0, 0
	 0, 0, 0, 0		 0, 0, 0, 0
	 3, 1, 1, 1		 0, 0, 0, 0
	 3, 1, 1, 1		 6, 2, 2, 2
   */
  test("move down, bottom 2 rows only calculate") {
    val nums = List(0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 3, 1, 1, 1)
    assert(move("down", nums) === List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 2, 2, 2))
  }

  /*
   	 3, 1, 1, 1  to  0, 0, 0, 0
	 0, 0, 0, 0		 0, 0, 0, 0
	 0, 0, 0, 0		 0, 0, 0, 0
	 3, 1, 1, 1		 6, 2, 2, 2
   */
  test("move down, top and bottom rows only calculate") {
    val nums = List(3, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1)
    assert(move("down", nums) === List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 2, 2, 2))
  }

  /*
   	 0, 0, 0, 0  to  0, 0, 0, 0
	 3, 1, 1, 1		 0, 0, 0, 0
	 3, 1, 1, 1		 0, 0, 0, 0
	 0, 0, 0, 0		 6, 2, 2, 2
   */
  test("move down, middle 2 rows only calculate") {
    val nums = List(0, 0, 0, 0, 3, 1, 1, 1, 3, 1, 1, 1, 0, 0, 0, 0)
    assert(move("down", nums) === List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 2, 2, 2))
  }

  /*
   	 3, 3, 1, 1  to  6, 2, 0, 0
	 2, 2, 2, 2		 4, 4, 0, 0
	 2, 2, 2, 2		 4, 4, 0, 0
	 2, 2, 2, 2		 4, 4, 0, 0
   */
  test("move left, all cells calculate") {
    val nums = List(3, 3, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
    assert(move("left", nums) === List(6, 2, 0, 0, 4, 4, 0, 0, 4, 4, 0, 0, 4, 4, 0, 0))
  }

  /*
   	 3, 0, 0, 3  to  6, 0, 0, 0
	 3, 0, 0, 3		 6, 0, 0, 0
	 1, 0, 0, 1		 2, 0, 0, 0
	 1, 0, 0, 1		 2, 0, 0, 0
   */
  test("move left, left and right columns only calculate") {
    val nums = List(3, 0, 0, 3, 3, 0, 0, 3, 1, 0, 0, 1, 1, 0, 0, 1)
    assert(move("left", nums) === List(6, 0, 0, 0, 6, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0))
  }

  /*
   	 0, 3, 3, 0  to  6, 0, 0, 0
	 0, 3, 3, 0		 6, 0, 0, 0
	 0, 1, 1, 0		 2, 0, 0, 0
	 0, 1, 1, 0		 2, 0, 0, 0
   */
  test("move left, middle 2 columns only calculate") {
    val nums = List(0, 3, 3, 0, 0, 3, 3, 0, 0, 1, 1, 0, 0, 1, 1, 0)
    assert(move("left", nums) === List(6, 0, 0, 0, 6, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0))
  }

  ////////
  /*
   	 3, 3, 1, 1  to  0, 0, 6, 2
	 2, 2, 2, 2		 0, 0, 4, 4
	 2, 2, 2, 2		 0, 0, 4, 4
	 2, 2, 2, 2		 0, 0, 4, 4
   */
  test("move right, all cells calculate") {
    val nums = List(3, 3, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
    assert(move("right", nums) === List(0, 0, 6, 2, 0, 0, 4, 4, 0, 0, 4, 4, 0, 0, 4, 4))
  }

  /*
   	 3, 0, 0, 3  to  0, 0, 0, 6
	 3, 0, 0, 3		 0, 0, 0, 6
	 1, 0, 0, 1		 0, 0, 0, 2
	 1, 0, 0, 1		 0, 0, 0, 2
   */
  test("move right, right and right columns only calculate") {
    val nums = List(3, 0, 0, 3, 3, 0, 0, 3, 1, 0, 0, 1, 1, 0, 0, 1)
    assert(move("right", nums) === List(0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0, 2, 0, 0, 0, 2))
  }

  /*
   	 0, 3, 3, 0  to  0, 0, 0, 6
	 0, 3, 3, 0		 0, 0, 0, 6
	 0, 1, 1, 0		 0, 0, 0, 2
	 0, 1, 1, 0		 0, 0, 0, 2
   */
  test("move right, middle 2 columns only calculate") {
    val nums = List(0, 3, 3, 0, 0, 3, 3, 0, 0, 1, 1, 0, 0, 1, 1, 0)
    assert(move("right", nums) === List(0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0, 2, 0, 0, 0, 2))
  }

  test("move left, insert random 2") {
    val nums = List(0, 3, 3, 0, 0, 3, 3, 0, 0, 1, 1, 0, 0, 1, 1, 0)
    val moveL = move("left", nums)
    assert(insertRandom(moveL).filter(_ == 0).length == 11
      && insertRandom(moveL).filter(_ == 2).length == 3)
  }

  test("move right, insert random 2") {
    val nums = List(0, 3, 3, 0, 0, 3, 3, 0, 0, 1, 1, 0, 0, 1, 1, 0)
    val moveR = move("right", nums)
    assert(insertRandom(moveR).filter(_ == 0).length == 11
      && insertRandom(moveR).filter(_ == 2).length == 3)
  }

  test("move down, insert random 2") {
    val nums = List(0, 3, 3, 0, 0, 3, 3, 0, 0, 1, 1, 0, 0, 1, 1, 0)
    val moveD = move("down", nums)
    assert(insertRandom(moveD).filter(_ == 0).length == 11
      && insertRandom(moveD).filter(_ == 2).length == 3)
  }

  test("insert random on full list") {
    val nums = List(4, 3, 3, 4, 4, 3, 3, 4, 4, 1, 1, 4, 4, 1, 1, 4)
    assert(insertRandom(nums).filter(_ == 2).length === 0)
  }
} 


