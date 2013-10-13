package TDPC_C

import java.io.InputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import java.io.PrintWriter

object Main {
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
  def main(args: Array[String]): Unit = {
    val ir = new InputReader(System.in)
    val k = ir.next().toInt
    val r = Array.ofDim[Int](1 << k)
    val dp = Array.ofDim[Double](k + 1, 1 << k)

    for (i <- 0 until 1 << k) {
      r(i) = ir.next.toInt
      dp(0)(i) = 1.0
    }
    val p = (i: Int, j: Int) => 1.0 / (1.0 + math.pow(10.0, (r(j) - r(i)) / 400.0))

    var w = 1
    for (a <- 1 to k) {
      w = w << 1
      var sum = 0.0
      for (i <- 0 until 1 << k) {
        var s = i/w*w
        var e = s+w/2
        if(s<=i && i<e){
          s += w/2
          e += w/2
        }
        for (j <- s until e)
          if (i != j) {
            dp(a)(i) += dp(a - 1)(j) * p(i, j)
          }
        dp(a)(i) *= dp(a - 1)(i)
      }
    }
    val pw = new PrintWriter(System.out)
    for (i <- 0 until 1 << k) pw.println(dp(k)(i))
    pw.flush()

  }

}