package validator

import model._

object WorkflowValidator {
  def zeroStepsValidation(wf: Workflow) = if (wf.steps.isEmpty) Some("Workflow has 0 steps") else None
  def noStartValidator(wf: Workflow): Option[String] = ???
  def fewStartsValidator(wf: Workflow): Option[String] = ???
  def orphansValidator(wf: Workflow): Option[String] = ???
}