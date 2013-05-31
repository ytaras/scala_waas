package validator

import model._

object WorkflowValidator {
  // These implementations could be shorter, but I tried to make them
  // easy to read for imperative programmers
  def zeroStepsValidation(wf: Workflow) = 
    if (wf.steps.isEmpty) Some("Workflow has 0 steps") else None
  def noStartValidator(wf: Workflow): Option[String] =
    if (wf.steps.filter { _.start}.isEmpty)
      Some("Workflow has 0 start steps") 
    else None
  def fewStartsValidator(wf: Workflow): Option[String] =
    if (wf.steps.filter { _.start }.length > 1 )
      Some("Workflow has more that 1 start step")
    else None
  def orphansValidator(wf: Workflow): Option[String] = {
    val references = wf.steps.flatMap { _.goesTo }.toSet
    val notReferenced = wf.steps.filterNot { step =>
      step.start || (references contains step.name)
    }.map { _.name }
    
    if(notReferenced.isEmpty) None
    else Some("There are steps that can't be reached")
  }
   
}