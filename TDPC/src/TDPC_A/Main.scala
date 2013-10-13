package TDPC_A

import java.io.{InputStreamReader, BufferedReader, InputStream}
import java.util.StringTokenizer

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

  def main(args: Array[String]) {
    val ir = new InputReader(System.in)
    val n = ir.next().toInt
    val dp = new Array[Boolean](10001)
    dp(0) = true
    for(i <- 1 to n){
      val p = ir.next().toInt
      for(k <- 10000 to p by -1)dp(k) |= dp(k-p)
    }
    println(dp.count(identity))
  }
}
