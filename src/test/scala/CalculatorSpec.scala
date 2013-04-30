import org.specs2.mutable._

class CalculatorSpec extends Specification {
  "Native scala operations" should {
    "add" in { 2 + 2 must_== 4 }
    "subtract" in { 5 - 4 must_== 1 }
    "mutliply" in { 3 * 4 must_== 12 }
    "divide" in { 10 / 2 must_== 5 }
  }
}
