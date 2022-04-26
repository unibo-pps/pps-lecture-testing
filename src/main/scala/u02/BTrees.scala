package u02

object BTrees:
  // A custom and generic binary tree of elements of type A
  enum Tree[A]:
    case Leaf(value: A)
    case Branch(left: Tree[A], right: Tree[A])

  object Tree:
    def size[A](t: Tree[A]): Int = t match
      case Branch(l, r) => size(l) + size(r)
      case _ => 1

    def find[A](t: Tree[A], elem: A): Boolean = t match
      case Branch(l, r) => find(l, elem) || find(r, elem)
      case Leaf(e) => e == elem

    def count[A](t: Tree[A], elem: A): Int = t match
      case Branch(l, r) => count(l, elem) + count(r, elem)
      case Leaf(e) if (e == elem) => 1
      case _ => 0

  @main
  def btreesMain() =
    import Tree.*
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(1))
    println(tree) // Branch(Branch(Leaf(1),Leaf(2)),Leaf(1))
    println(size(tree)) // ..,3
    println(find(tree, 1)) // true
    println(find(tree, 4)) // false
    println(count(tree, 1)) // 2
