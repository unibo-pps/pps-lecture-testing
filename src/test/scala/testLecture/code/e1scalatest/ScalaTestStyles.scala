package testLecture.code.e1scalatest

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

// @RunWith(classOf[JUnitRunner])
class BasicTest extends AnyFunSuite with Matchers:
  test("Simple test") {
    true shouldBe true
  }

// @RunWith(classOf[JUnitRunner])
class BasicFlatSpec extends AnyFlatSpec with Matchers:
  "An empty set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "raise NoSuchElementException for head" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }

// @RunWith(classOf[JUnitRunner])
class BasicFunSpec extends AnyFunSpec with Matchers:
  describe(" A Set ") {
    describe(" when empty ") {
      it(" should have size 0 ") {
        assert(Set.empty.size == 0)
      }
      it("should raise NoSuchElementException for head") {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
    }

    describe(" when not empty"){
      pending
    }
  }

  describe("Two sets") {
    pending
  }

class ScalaTestExampleWithoutRunWithAnnotation extends AnyFunSuite:
  test("simple test"){ }
