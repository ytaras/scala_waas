import org.specs2.mutable._
import org.specs2.ScalaCheck

class CalculatorSpec extends Specification with ScalaCheck {
  "Native scala operations" should {
    "add" in { 2 + 2 must_== 4 }
    "subtract" in { 5 - 4 must_== 1 }
    "mutliply" in { 3 * 4 must_== 12 }
    "divide" in { 10 / 2 must_== 5 }
  }
  "Calculator" should {
    "add" in { Calculator.add(3, 3) must_== 6 }
    "subtract" in { Calculator.subtract(10, 3) must_== 7 }
    "multiply" in { Calculator.multiply(5, 5) must_== 25 }
    "divide"   in { Calculator.divide(30, 3) must_== 10 }
  }
  "Calculator properties" should {
    "divide" in prop { (x: Int, y: Int) => (y != 0) ==> (
      Calculator.divide(x, y) must_== x / y
    )}
    "add" in prop { (x: Int, y: Int) =>
      Calculator.add(x, y) must_== x + y
    }
  }
}
