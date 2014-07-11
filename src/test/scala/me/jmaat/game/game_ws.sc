package justin
  
import scala.util._
  
object game_ws {


//	def add(nums: List[Int]): List[Int] = {
//		if (x == y) x + y
//		else y
//	}
	
  /**
 	A, B, C, D		0,  1,  2,  3
 	E, F, G, H		4,  5,  6,  7
 	I, J, K, L		8,  9,  10, 11
 	M, N, O, P		12, 13, 14, 15
 	
   */
  
  def calc(nums: List[Int]): List[Int] = {
    def calcAcc(nextNums: List[Int], numsAcc: List[Int]): List[Int] = nextNums match {
      case List() => numsAcc.reverse.padTo(4, 0).reverse
      case x :: xs =>
        if (xs.isEmpty || x != xs.head) calcAcc(xs, x :: numsAcc)
        else calcAcc(xs.tail, (x + xs.head) :: numsAcc)
    }
    calcAcc(nums.filter(p => p != 0).reverse, List())
  }                                               //> calc: (nums: List[Int])List[Int]
  
	val start0 = List(2,0,2,2)                //> start0  : List[Int] = List(2, 0, 2, 2)
	val l = List("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P")
                                                  //> l  : List[String] = List(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P)
	val nums = List(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15)
                                                  //> nums  : List[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 1
                                                  //| 5)
  val nums2 = List(3, 3, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
                                                  //> nums2  : List[Int] = List(3, 3, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
  def right(nums: List[Int]): List[List[Int]] = nums.grouped(4).toList
                                                  //> right: (nums: List[Int])List[List[Int]]
  
  val n = right(nums2)                            //> n  : List[List[Int]] = List(List(3, 3, 1, 1), List(2, 2, 2, 2), List(2, 2, 2
                                                  //| , 2), List(2, 2, 2, 2))
  
  calc(n(0))                                      //> res0: List[Int] = List(0, 0, 6, 2)
 
  val d =
  	(for {
      x <- right(nums2)
     } yield (calc(x))).flatten                   //> d  : List[Int] = List(0, 0, 6, 2, 0, 0, 4, 4, 0, 0, 4, 4, 0, 0, 4, 4)
  
  //up(d).flatten.reverse
	//down(d).flatten
	val calced = right(d).flatten             //> calced  : List[Int] = List(0, 0, 6, 2, 0, 0, 4, 4, 0, 0, 4, 4, 0, 0, 4, 4)
                                                  //| 
	calced.zipWithIndex                       //> res1: List[(Int, Int)] = List((0,0), (0,1), (6,2), (2,3), (0,4), (0,5), (4,
                                                  //| 6), (4,7), (0,8), (0,9), (4,10), (4,11), (0,12), (0,13), (4,14), (4,15))
	
	val c = calced.zipWithIndex.filter(_._1 == 0).map(_._2)
                                                  //> c  : List[Int] = List(0, 1, 4, 5, 8, 9, 12, 13)
  c(8)                                            //> java.lang.IndexOutOfBoundsException: 8
                                                  //| 	at scala.collection.LinearSeqOptimized$class.apply(LinearSeqOptimized.sc
                                                  //| ala:52)
                                                  //| 	at scala.collection.immutable.List.apply(List.scala:84)
                                                  //| 	at justin.game_ws$$anonfun$main$1.apply$mcV$sp(justin.game_ws.scala:52)
                                                  //| 
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at justin.game_ws$.main(justin.game_ws.scala:5)
                                                  //| 	at justin.game_ws.main(justin.game_ws.scala)
  
	val z = new Random(5)
	z.nextInt()
	
}