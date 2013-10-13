package TDPC_B

import java.io.{ InputStreamReader, BufferedReader, InputStream }
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
    val a, b = ir.next().toInt
    val va = Array.ofDim[Int](a)
    val vb = Array.ofDim[Int](b)
    for (i <- 0 until a) va(i) = ir.next().toInt
    for (j <- 0 until b) vb(j) = ir.next().toInt
    val dp = Array.ofDim[Int](a + 1, b + 1)
    for (i <- 0 to a; j <- 0 to b) if (i + j > 0) dp(i)(j) = -1000000
    val cong = (i: Int, j: Int) => ((i + j) & 1) == ((a + b) & 1)
    for (i <- 0 to a; j <- 0 to b) {
      if (cong(i, j)) {
        (i, j) match {
          case (0, 0) => {}
          case (0, _) => dp(0)(j) = dp(0)(j - 1) + vb(b - j)
          case (_, 0) => dp(i)(0) = dp(i - 1)(0) + va(a - i)
          case (_, _) => dp(i)(j) = math.max(dp(i - 1)(j) + va(a - i), dp(i)(j - 1) + vb(b - j))
        }
      } else {
        (i, j) match {
          case (0, 0) => {}
          case (0, _) => dp(0)(j) = dp(0)(j - 1)
          case (_, 0) => dp(i)(0) = dp(i - 1)(0)
          case (_, _) => dp(i)(j) = math.min(dp(i - 1)(j), dp(i)(j - 1))
        }
      }
      //println(i+" "+j+" "+dp(i)(j))
    }
    println(dp(a)(b))
  }
}