2048 implemented in Scala
=========================

I've added 3 levels of difficulty to the original 2048 game (easy | medium | hard)

## Try it out here!  
http://jmaat.me/2048-Scala
  

Please note, this is NOT a good example of scala code :)  Just something I was playing around with while on an airplane ride back from a conference in Denver.  I tweaked it a bunch, but it's most likely missing a bunch of unit tests, could use refactoring, etc..  

The basic idea is to make a call to a REST service which calculates the entire game board in 1 step.  

For example 
a grid board like -

 1, 2, 3, 4  
 1, 2, 7, 8  
 9, 7, 4, 5  
 8, 5, 4, 3

Move up, will call the service as -  

[POST] 2048-Scala/move  
data:  {  
  direction: "up",  
  nums: [1,2,3,4,1,2,7,8,9,7,4,5,8,5,4,3]
  mode: "easy"  
}  

And return the resulting list after transposing and summing any matching sibling cell (on upwards motion), then insert a random 2 in an open spot. 

returns  
[2,4,3,4,,9,7,7,8,8,5,8,5,0,2,0,3]


And I use Jquery to reset the board -   
 2, 4, 3, 4  
 9, 7, 7, 8  
 8, 5, 8, 5  
 0, 2, 0, 3  

