package TDPC_F

object Main {

  def main(args: Array[String]): Unit = {
    val mod = 1000000007
    val Array(n,k) = readLine.split(" ").map(_.toInt)
    val dp = Array.ofDim[Int](n+k)
    var ps = 0
    dp(k-2) = 1
    for(i <- 0 until k)ps += dp(i)
    for(i <- k until k+n-1){
      dp(i) = ps
      ps += dp(i)-dp(i-k)
      ps = (ps%mod+mod)%mod
    }
    println(((dp(n+k-2)-dp(n-2))%mod+mod)%mod)
  }

}