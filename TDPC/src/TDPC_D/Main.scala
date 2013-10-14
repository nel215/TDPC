package TDPC_D

import scala.collection.mutable.HashMap

object Main {

  def main(args: Array[String]): Unit = {
    var Array(n,d) = readLine.split(" ").map(_.toLong)
    def f(div:Int):Int = {
      if(d%div==0){
        d /= div
        return f(div)+1
      }
      return 0
    }
    val (c2,c3,c5) = (f(2),f(3),f(5))
    if(d!=1){
      println(0.0)
    }else{
      var memo:HashMap[(Int,Int,Int,Int),Double] = new HashMap()
      def rec(i:Int,j2:Int,j3:Int,j5:Int):Double = {
        if(i==n){
          if(j2==0 && j3==0 && j5==0)return 1.0
          else 0.0
        }else{
          if(memo.contains(i,j2,j3,j5))return memo(i,j2,j3,j5)
          var res = 0.0
          for((k2,k3,k5) <- List((0,0,0),(1,0,0),(0,1,0),(2,0,0),(0,0,1),(1,1,0))){
            res += rec(i+1,math.max(j2-k2,0),math.max(j3-k3,0),math.max(j5-k5,0))
          }
          res /= 6.0
          memo += ((i,j2,j3,j5)->res)
          return res
        }
      }
      println(rec(0,c2,c3,c5))
    }
  }

}