
package comp

import comp.cradle.Cradle

object Main {

  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]): Unit = {
    val cradle = Cradle()
    cradle.init
    cradle.expression
  }

}
