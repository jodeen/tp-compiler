
package comp.cradle

import java.io.InputStream
import java.io.PrintStream

class Cradle(val inStream : InputStream, val outStream : PrintStream) {
  val TAB = '\t'
  var look : Char = '\0'
  
  var exit = (code:Int) => System.exit(code)

  def getChar() = {
    look = inStream.read.toChar
  }
  def error(s:String) = {
    println
    println("\t Error: " + s + ".")    
    throw new RuntimeException(s)
  }
  
  def abort(s:String) = {
    error(s)
    exit(-1);
  }
  
  def expected(s:String) = {
    abort(s + " Expected")
  }
  
  def matchChar(x:Char) = {
    if (look.equals(x)) getChar
    else expected("'" + x + "'")
  }
  
  def isAlpha(c:Char) : Boolean = {
    c.isLetter
  }
  
  def isDigit(c:Char) : Boolean = {
    c.isDigit
  }
  
  def isAddop(c: Char): Boolean = {
    List('+','-').contains(c)
  }
  
  def getName() : Char = {
    if (!isAlpha(look)) expected("Name")
    val res = look;
    getChar
    res
  }
  
  def getNum : Char = {
    if (!isDigit(look)) expected("Integer")
    val res = look;
    getChar
    res
  }
  
  def emit(s:String) = {
    outStream.print(TAB + s)
  }
  
  def emitLn(s:String) = {
    emit(s)
    println
  }
  
  def init = {
    getChar
  }
  
  def term : Unit = {
    factor
    while (List('*','/').contains(look)) {
      emitLn("MOVE D0,-(SP)")
      look match {
        case '*' => multiply
        case '/' => divide
        case _ => expected("Mulop")
      }
    }
  }
  
  def expression = {
    if (isAddop(look)) {
      emitLn("CLR D0")
    } else {
      term
    }
    while (List('+','-').contains(look)) {
      emitLn("MOVE D0,-(SP)")
      look match {
        case '+' => add
        case '-' => subtract
        case _ => expected("Addop")
      }
    }
  }
  
  def factor = {
    if (look.equals('(')) {
      matchChar('(')
      expression
      matchChar(')')
    } else {
      emitLn("MOVE #" + getNum + ",D0");
    }
  }
  
  def add = {
    matchChar('+')
    term 
    emitLn("ADD (SP)+,D0")
  }
  
  def subtract = {
    matchChar('-')
    term 
    emitLn("SUB (SP)+,D0")
    emitLn("NEG D0")
  }
  
  def multiply = {
    matchChar('*')
    factor
    emitLn("MULS (SP)+,D0")
  }
  
  def divide = {
    matchChar('/')
    factor
    emitLn("MOVE (SP)+,D1")
    emitLn("DIVS D1,D0")
  }
}

object Cradle {
  def apply() : Cradle = new Cradle(System.in, System.out)
  def apply(inStream : InputStream, outStream : PrintStream) : Cradle = new Cradle(inStream, outStream)
  def apply(inStream : InputStream, outStream : PrintStream, exit: Int => Unit) : Cradle = {
    val c = new Cradle(inStream, outStream)
    c.exit = exit;
    c
  }
}
