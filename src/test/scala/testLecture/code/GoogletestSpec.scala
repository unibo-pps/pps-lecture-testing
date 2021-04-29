package testLecture.code

import org.junit.runner.RunWith
import org.scalatest.selenium._
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.Eventually._
import org.scalatest.junit.JUnitRunner

import scala.concurrent.duration._

@RunWith(classOf[JUnitRunner])
class GoogletestSpec extends FlatSpec with Matchers with HtmlUnit {

  "Google search" should "work" in {
    go to "http://www.google.com"
    pageTitle should be ("Google")
    executeScript("return document.title") shouldEqual pageTitle

    click on "q" // By name of the field
    textField("q").value = "selenium"
    submit()

    eventually(timeout(2 seconds)) {
      pageTitle should startWith ("selenium - ")
    }
  }
}