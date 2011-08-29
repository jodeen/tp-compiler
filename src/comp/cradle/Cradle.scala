
package comp.cradle

object Cradle {
  val TAB = '\t'
  var look : Char = '\0'
  var inStream = System.in

  def getChar() = {
    look = inStream.read.toChar
  }
  def error(s:String) = {
    println
    println("\t Error: " + s + ".")    
  }
  
  def abort(s:String) = {
    error(s)
    System.exit(-1);
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
  
  def getName() : Char = {
    if (!isAlpha(look)) expected("Name")
    val res = look;
    getChar
    res
  }
  
  def getNum() : Char = {
    if (!isDigit(look)) expected("Integer")
    val res = look;
    getChar
    res
  }
  
  def emit(s:String) = {
    print(TAB + s)
  }
  
  def emitLn(s:String) = {
    emit(s)
    println
  }
  
  def init = {
    getChar
  }
  
}
