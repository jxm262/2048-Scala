package justin
  
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
  val nums2 = List(3,1,1,1,3,1,1,1,2,2,2,2,2,2,2,2)
                                                  //> nums2  : List[Int] = List(3, 1, 1, 1, 3, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2)
  
  def up(nums: List[Int]): List[List[Int]] = (for {
    x <- 0 to 3
    y <- x to 15 by 4
  } yield (nums(y))).toList.reverse.grouped(4).toList.reverse
                                                  //> up: (nums: List[Int])List[List[Int]]
  def down(nums: List[Int]): List[List[Int]] = (for {
    x <- 0 to 3
    y <- x to 15 by 4
  } yield (nums(y))).toList.grouped(4).toList     //> down: (nums: List[Int])List[List[Int]]
  
  val n = down(nums2)                             //> n  : List[List[Int]] = List(List(3, 3, 2, 2), List(1, 1, 2, 2), List(1, 1, 
                                                  //| 2, 2), List(1, 1, 2, 2))
  
  calc(n(0))                                      //> res0: List[Int] = List(0, 0, 6, 4)
 
  val d =
  	(for {
      x <- up(nums2)
     } yield (calc(x))).flatten                   //> d  : List[Int] = List(0, 0, 4, 6, 0, 0, 4, 2, 0, 0, 4, 2, 0, 0, 4, 2)
  
  //up(d).flatten.reverse
	//down(d).flatten
	up(d).flatten.reverse                     //> res1: List[Int] = List(6, 2, 2, 2, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0)
	
	/**
	 3, 1, 1, 1
	 3, 1, 1, 1
	 2, 2, 2, 2
	 2, 2, 2, 2
	 
	 0, 0, 0, 0
	 0, 0, 0, 0
	 6, 2, 2, 2
	 4, 4, 4, 4
	
	 6, 2, 2, 2
	 4, 4, 4, 4
	 0, 0, 0, 0
	 0, 0, 0, 0
	 
	*/
}