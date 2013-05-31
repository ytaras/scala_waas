package validator

import org.specs2.mutable._
import org.specs2.ScalaCheck
import model._

class ValidationSpec extends Specification {
  import WorkflowValidator._
  val valid = Workflow("wf", List(
    Step("s1", List("s2", "s3"), true),
    Step("s2", List("s3"), false),
    Step("s3", List("s2"), false)
  ))
  "Workflow validator" should {
     "fail workflow without start stage" in { pending }
     "fail workflow with few start stages" in { pending }
     "fail wokrflow with orphan stages" in { pending }
     "pass successfull workflow" in { pending }
  }
  "zero step validator" should {
     "return None for valid workflow" in {
       zeroStepsValidation(valid) must beNone 
     }
     "return Some for invalid workflow" in {
       zeroStepsValidation(Workflow("wf", Nil)) must beSome
     }
  }
  "no start validator" should {
     "return None for valid workflow" in {
       noStartValidator(valid) must beNone 
     }
     "return Some for invalid workflow" in {
       noStartValidator(Workflow("wf", List(
         Step("s1", Nil, false)
       ))) must beSome
     }
  }
  "orphan steps validator" should {
     "return None for valid workflow" in {
       fewStartsValidator(valid) must beNone 
     }
     "return Some for invalid workflow" in {
       fewStartsValidator(Workflow("wf", List(
         Step("s1", Nil, true),
         Step("s2", Nil, false)
       ))) must beSome
     }
  }
  "zero step validator" should {
     "return None for valid workflow" in {
       zeroStepsValidation(valid) must beNone 
     }
     "return Some for invalid workflow" in {
       zeroStepsValidation(Workflow("wf", Nil)) must beSome
     }
  }
}