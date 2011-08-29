
package comp

import comp.cradle.Cradle

object Main {

  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]): Unit = {
    val cradle = new Cradle(System.in, System.out)
    cradle.init
    cradle.expression
  }

}
