
package comp.cradle

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.Test;
import org.junit.Assert._;

class CradleTest {
  @Test
  def testExpression() = {
    val bos = new ByteArrayOutputStream
    val cradle = Cradle(buildInput("1+2"), new PrintStream(bos))
    cradle.init
    cradle.expression
    assertEquals("\tMOVE #1,D0\n\t", new String(bos.toByteArray))
  }

  @Test
  def testExpression_Invalid() = {
    val bos = new ByteArrayOutputStream
    val cradle = Cradle(buildInput("a"), new PrintStream(bos),(a) => Unit)
    cradle.init
    cradle.expression
    assertEquals("\tInteger expected", new String(bos.toByteArray))
  }

  
  def buildInput(s : String) = new ByteArrayInputStream(s.getBytes("UTF-8"))
}
