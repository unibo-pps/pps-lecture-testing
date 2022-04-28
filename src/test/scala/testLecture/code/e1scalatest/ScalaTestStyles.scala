package testLecture.code.e1scalatest

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class BasicTest extends AnyFunSuite with Matchers:
  test("Simple test") {
    true shouldBe true
  }

class BasicFlatSpec extends AnyFlatSpec with Matchers:
  "An empty set" should "have size 0" in {
    assert(Set.empty.size == 0)
    Set.empty.size shouldBe 0
    Set.empty should have size 0
  }

  it should "raise NoSuchElementException for head" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }

class SetSuite extends AnyFunSuite:
  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Calling head on an empty Set should yield NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }

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


import org.scalatest.matchers.must.{ Matchers as MustMatchers }

class StringSuite extends AnyFunSuite with MustMatchers:
  test("Some check with regex") {
    "abbccxxx" must startWith regex ("a(b*)(c*)" withGroups ("bb", "cc"))
  }

class ScalaTestExampleWithoutRunWithAnnotation extends AnyFunSuite:
  test("simple test"){ }
