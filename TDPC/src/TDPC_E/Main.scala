package TDPC_E

import java.io.InputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class InputReader(stream: InputStream) {
  private val reader = new BufferedReader(new InputStreamReader(stream))
  private var tokenizer: StringTokenizer = new StringTokenizer(reader.readLine())
  def next(): String = {
    while (!tokenizer.hasMoreTokens()) {
      tokenizer = new StringTokenizer(reader.readLine())
    }
    tokenizer.nextToken()
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val mod = 1000000007
    val ir = new InputReader(System.in)
    val d = ir.next().toInt
    val n = ir.next()
    val dp = Array.ofDim[Int](n.length() + 1, d, 2)
    dp(0)(0)(1) = 1
    for (i <- 0 until n.length()) {
      for (j <- 0 to 1) {
        for (k <- 0 to 9) {
          for (a <- 0 until d) if(dp(i)(a)(j)>0){
            val b = (a + k) % d
            if (j == 1) {
              if (k + '0' == n(i)) dp(i + 1)(b)(1) = (dp(i + 1)(b)(1) + dp(i)(a)(1)) % mod
              else if (k + '0' < n(i)) dp(i + 1)(b)(0) = (dp(i + 1)(b)(0) + dp(i)(a)(1)) % mod
            } else {
              dp(i + 1)(b)(0) = (dp(i + 1)(b)(0) + dp(i)(a)(0)) % mod
            }
          }
        }
      }
    }
    println((dp(n.length())(0)(1)+dp(n.length())(0)(0)-1+mod)%mod)
  }
}