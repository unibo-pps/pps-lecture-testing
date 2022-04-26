package testLecture.code.e3propertyBased

// import org.junit.runner.RunWith
import org.scalacheck.{Gen, Prop}
// import org.scalatest.junit.JUnitRunner
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.matchers.should._
import org.scalatest.propspec.AnyPropSpec

import scala.collection.immutable.{BitSet, HashSet, TreeSet}

// @RunWith(classOf[JUnitRunner])
class SetSpec extends AnyPropSpec with TableDrivenPropertyChecks with Checkers with Matchers:
  val examples = Table("set", BitSet.empty, HashSet.empty[Int], TreeSet.empty[Int])
  val examplesGen = Gen.oneOf(BitSet.empty, HashSet.empty[Int], TreeSet.empty[Int])

  property("an empty Set should have size 0") {
    // ScalaTest's property-based testing style
    forAll(examples) { set => set.size should be (0) }
    // ScalaCheck's property-based testing style
    check { Prop.forAll(examplesGen) { set => set.size == 0 } }
  }

  property("invoking head on an empty set should produce NoSuchElementException") {
    // ScalaTest's property-based testing style
    forAll(examples) { set => a [NoSuchElementException] should be thrownBy{set.head} }
    // ScalaCheck's property-based testing style
    check { Prop.forAll(examplesGen) { set =>
      Prop.throws(classOf[NoSuchElementException])(set.head) }
    }
  }
