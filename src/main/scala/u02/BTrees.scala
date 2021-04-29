package u02

import u02.BTrees.Tree
import u02.BTrees.Tree.{Branch, Leaf, count, find, size}

object BTrees {

  // A custom and generic binary tree of elements of type A
  sealed trait Tree[A]
  object Tree {
    case class Leaf[A](value: A) extends Tree[A]
    case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

    def size[A](t: Tree[A]): Int = t match {
      case Branch(l, r) => size(l) + size(r)
      case _ => 1
    }

    def find[A](t: Tree[A], elem: A): Boolean = t match {
      case Branch(l, r) => find(l, elem) || find (r,elem)
      case Leaf(e) => e==elem
    }

    def count[A](t: Tree[A], elem: A): Int = t match {
      case Branch(l, r) => count(l, elem) + count(r,elem)
      case Leaf(e) if (e==elem) => 1
      case _ => 0
    }
  }
}

object BTreesApp extends App {
  import Tree._
  val tree = Branch(Branch(Leaf(1),Leaf(2)),Leaf(1))
  println(tree, size(tree)) // ..,3
  println( find(tree, 1)) // true
  println( find(tree, 4)) // false
  println( count(tree, 1)) // 2
}
