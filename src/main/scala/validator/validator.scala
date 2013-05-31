package validator

import model._

object WorkflowValidator {
  def zeroStepsValidation(wf: Workflow): Option[String] = if (wf.steps.isEmpty) Some("Workflow has 0 steps") else None
}