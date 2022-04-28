package testLecture.code.e5more

import org.junit.runner.RunWith
import org.openqa.selenium.By
import org.scalatest.concurrent.Eventually.*
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.selenium.HtmlUnit
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.duration.*

class GoogletestSpec extends AnyFlatSpec with Matchers with HtmlUnit:
  "Google search" should "work" in {
    this.webDriver.setJavascriptEnabled(false) // to avoid scripting exceptions
    pending // must by-pass the cookie consent -- cf. https://stackoverflow.com/questions/65798231/selenium-webdriver-how-to-bypass-google-accept-cookies-dialog-box

    go to "http://www.google.com"
    pageTitle should be ("Google")
    executeScript("return document.title") shouldEqual pageTitle

    click on "q" // By name of the field
    textField("q").value = "selenium"
    submit()

    eventually(timeout(2.seconds)) {
      pageTitle should startWith ("selenium - ")
    }
  }