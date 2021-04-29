package testLecture.code.e0basics

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import testLecture.code.ProgramToCover

// sbt
// > coverage
// > testOnly *ConditionCoverageExample
// > coverageReport

@RunWith(classOf[JUnitRunner])
class ConditionCoverageExample extends FunSuite {
  test("Cover all the conditions") {
    ProgramToCover.methodToCover(false, true, false, true, true)
    ProgramToCover.methodToCover(true, false, true, false, false)
  }
}

@RunWith(classOf[JUnitRunner])
class DecisionCoverageExample extends FunSuite {
  test("Cover all the decision branches") {
    ProgramToCover.methodToCover(true, true, false, false, true)
    ProgramToCover.methodToCover(false, false, true, false, false)
  }
}