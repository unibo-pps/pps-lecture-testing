package testLecture.code.steps

import cucumber.api.scala.{EN, ScalaDsl}
import org.junit.Assert

class MyFeatureSteps extends ScalaDsl with EN {
  var (a, b, sum) = (0, 0, 0)

  Given("""^one operand (\d+)$"""){ a = (_:Int) }
  Given("""^another operand (\d+)$"""){ b = (_:Int) }
  When("""^I multiply them together$"""){ sum = a*b  }
  Then("""^I should obtain result (\d+)$"""){ (expectedSum:Int) =>
    Assert.assertEquals(expectedSum, sum)
  }
}