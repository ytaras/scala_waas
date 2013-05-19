package model

case class Step(name: String, goesTo: List[String], start: Boolean)
case class Workflow(name: String, steps: List[Step])