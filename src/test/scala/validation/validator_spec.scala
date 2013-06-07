package validator

import org.specs2.mutable._
import org.specs2.ScalaCheck
import util.ValidationMatcher
import model._
import scalaz._
import Scalaz._

class ValidationSpec extends Specification
    with ValidationMatcher with NonEmptyListFunctions {
  import WorkflowValidator._

  val valid = Workflow("wf", List(
    Step("s1", List("s2", "s3"), true),
    Step("s2", List("s3"), false),
    Step("s3", List("s2"), false)
  ))
  val emptyWorkflow = Workflow("wf", Nil)
  val noStartWorkflow = Workflow("wf", List(
    Step("s1", Nil, false)
  ))
  val orphansWorkflow = Workflow("wf", List(
    Step("s1", List("s3"), true),
    Step("s2", List("s3"), false),
    Step("s3", List("s1"), false)
  ))
  val fewStartsWorkflow = Workflow("wf", List(
    Step("s1", List("s2"), true),
    Step("s2", Nil, true)
  ))
  "Workflow validator" should {
    "fail workflow without any steps" in {
      validate(emptyWorkflow) must
        failWith(nels("Workflow has 0 steps", "Workflow has 0 start steps"))
    }
    "fail workflow without start step" in {
      validate(noStartWorkflow) must
        failWith(nels("Workflow has 0 start steps",
          "There are steps that can't be reached"))
    }
    "fail workflow with few start steps" in {
      validate(fewStartsWorkflow) must
        failWith("Workflow has more that 1 start step".wrapNel)
    }
    "fail workflow with orphan steps" in {
      validate(orphansWorkflow) must
        failWith("There are steps that can't be reached".wrapNel)
    }
    "pass successfull workflow" in { validate(valid) must succeedWith(valid) }
  }
  "zero step validator" should {
     "return None for valid workflow" in {
       zeroStepsValidation(valid) must beNone
     }
     "return Some for invalid workflow" in {
       zeroStepsValidation(emptyWorkflow) must beSome
     }
  }
  "no start validator" should {
     "return None for valid workflow" in {
       noStartValidator(valid) must beNone
     }
     "return Some for invalid workflow" in {
       noStartValidator(noStartWorkflow) must beSome
     }
  }
  "orphan steps validator" should {
     "return None for valid workflow" in {
       orphansValidator(valid) must beNone
     }
     "return Some for invalid workflow" in {
       orphansValidator(orphansWorkflow) must beSome
     }
  }
  "few start steps validator" should {
     "return None for valid workflow" in {
       fewStartsValidator(valid) must beNone
     }
     "return Some for invalid workflow" in {
       fewStartsValidator(fewStartsWorkflow) must beSome
     }
  }
}
