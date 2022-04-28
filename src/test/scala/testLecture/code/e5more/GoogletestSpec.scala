package testLecture.code.e5more

import org.junit.runner.RunWith
import org.scalatest.concurrent.Eventually.*
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.HtmlUnit
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.duration._

class GoogletestSpec extends AnyFlatSpec with Matchers with HtmlUnit:
  "Google search" should "work" in {
    go to "http://www.google.it"
    pageTitle should be ("Google")
    executeScript("return document.title") shouldEqual pageTitle

    click on "q" // By name of the field
    textField("q").value = "selenium"
    submit()

    eventually(timeout(2.seconds)) {
      pageTitle should startWith ("selenium - ")
    }
  }