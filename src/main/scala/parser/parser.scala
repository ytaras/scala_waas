package parsers

import scala.util.parsing.combinator._

object SampleParser extends JavaTokenParsers {
  def number = floatingPointNumber
  def twoNumbers = floatingPointNumber ~ floatingPointNumber
}

object ParserExample extends App {
  import SampleParser._

  println("Parsing whole string '123' as a number")
  println(parseAll(number, "123").get)
  println("Parsing whole string '123 321' as a number")
  parseAll(number, "123 123") match {
    case x: NoSuccess => println(x.msg)
  }
  println("Parsing string '123 321' as a number")
  println(parse(number, "123").get)
  println("Parsing whole string '123 321' as two numbers")
  println(parseAll(twoNumbers, "123 321").get)
}
