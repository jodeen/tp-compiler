
package comp.cradle

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.Test;
import org.junit.Assert._;

class CradleTest {
  @Test
  def testExpression() = {
    println("Hello")
    val bos = new ByteArrayOutputStream
    val cradle = new Cradle(buildInput("1"), new PrintStream(bos))
  }
  
  def buildInput(s : String) = new ByteArrayInputStream(s.getBytes("UTF-8"))
}
