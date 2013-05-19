package parsers

import org.specs2.mutable._
import org.specs2.ScalaCheck
import WorkflowParsers._
import model._

class WorkflowParserSpec extends Specification {
  "Parser" should {
    "parse goes to" in { 
       parseAll(goesTo, "goes to one, two, three").get must_== 
         List("one", "two", "three")
    }
    "parse start step" in {
      parseAll(WorkflowParsers.step, "start step name goes to one, two, three;").get must_==
         Step("name", List("one", "two", "three"), true)
    }
    "parse empty step" in {
      parseAll(WorkflowParsers.step, "step name;").get must_==
         Step("name", List(), false)
    }
    "parse empty workflow" in {
      parseAll(workflow, """workflow wf {};""").get must_==
         Workflow("wf", List())
    }
    "parse not-empty workflow" in {
      parseAll(workflow, """workflow wf {
                              start step one goes to two;
                              step two;
                            };""").get must_==
         Workflow("wf", List(Step("one", List("two"), true), Step("two", List(), false)))
    }
  }
}