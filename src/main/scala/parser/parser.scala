package parsers

import scala.util.parsing.combinator._
import model._

object SampleParser extends JavaTokenParsers {
  def number: Parser[Float] = floatingPointNumber ^^ { _.toFloat }
  def twoNumbers: Parser[Float] = number ~ number ^^ { x => 
    x._1 * x._2
  }
}

object WorkflowParsers extends JavaTokenParsers {
  def workflows: Parser[List[Workflow]] = workflow.*
  def workflow: Parser[Workflow] = ("workflow" ~> ident <~ "{") ~ step.* <~ ("}" ~ ";") ^^ {
    _ => ???
  }
  def step: Parser[Step] = ("start".? <~ "step") ~ ident ~ goesTo.? <~ ";" ^^ {
     _ => ???
  }
  def goesTo: Parser[List[String]] = ("goes" ~ "to") ~> (ident <~ ",").* ~ ident ^^ {
     case list ~ last => list :+ last
  }
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
