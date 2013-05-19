package parsers

import org.specs2.mutable._
import org.specs2.ScalaCheck
import WorkflowParsers._

class WorkflowParserSpec extends Specification {
  "Parser" should {
    "parse goes to" in { 
       parseAll(goesTo, "goes to one, two, three").get must_== 
         List("one", "two", "three")
    }
  }
}