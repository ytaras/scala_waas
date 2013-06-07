package validator

import model._
import scalaz._
import syntax.std.list._
import syntax.std.option._
import syntax.std.boolean._

object WorkflowValidator {
  def validate(workflow: Workflow) =
    validators.map { _.apply(workflow) }.flatten.toNel.toFailure(workflow)

  private val validators: List[Workflow => Option[String]] = List(
    zeroStepsValidation, noStartValidator, fewStartsValidator, orphansValidator
  )

  private def zeroStepsValidation(wf: Workflow) =
    wf.steps.isEmpty.option("Workflow has 0 steps")
  private def noStartValidator(wf: Workflow): Option[String] =
    wf.steps.filter { _.start}.isEmpty.option("Workflow has 0 start steps")
  private def fewStartsValidator(wf: Workflow): Option[String] =
    (wf.steps.filter { _.start }.length > 1 ).
      option("Workflow has more that 1 start step")
  private def orphansValidator(wf: Workflow): Option[String] = {
    val references = wf.steps.flatMap { _.goesTo }.toSet
    val notReferenced = wf.steps.filterNot { step =>
      step.start || (references contains step.name)
    }.map { _.name }

    (!notReferenced.isEmpty).option("There are steps that can't be reached")
  }

}
