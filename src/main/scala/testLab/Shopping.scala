package testLab

case class Product(name: String)
case class Price(value: Double)
case class Item(product: Product, details: ItemDetails)
case class ItemDetails(qty: Int, price: Price)

trait Cart {
  def add(item: Item)
  def content: Set[Item]
  def size: Int
  def totalCost: Double
}

class BasicCart(private var items: Map[Product, ItemDetails] = Map()) extends Cart {
  def add(item: Item) = {
    items += (item.product -> items.get(item.product).map(i =>
      i.copy(qty = i.qty + item.details.qty,
      price = Price(i.price.value+item.details.price.value))).getOrElse(item.details))
  }
  def content: Set[Item] = items.map { case (prod,details) => Item(prod,details) } toSet
  def size = items.size
  def totalCost: Double = items.values.foldRight(0.0)(_.price.value+_)
}

trait Catalog {
  def products: Iterable[(Product, Price)]
  def priceFor(p: Product, qty: Int): Price
  def priceFor(p: Product): Price = priceFor(p, qty=1)
}

class BasicCatalog(map: Map[Product,Price]) extends Catalog {
  def products = map
  def priceFor(p: Product, qty: Int): Price = Price(map(p).value * qty)
}

trait Warehouse {
  def get(p: Product, qty: Int): (Product, Int)
  def supply(p: Product, qty: Int): Unit
}

class BasicWarehouse extends Warehouse{
  private var products: Map[Product,Int] = Map[Product,Int]()
  override def supply(p: Product, qty: Int): Unit = products += p -> (products.getOrElse(p,0) + qty)
  override def get(p: Product, qty: Int): (Product,Int) = {
    val availability = products.getOrElse(p, 0)
    availability match {
      case 0 => (p, 0)
      case _ if availability >= qty => {
        val newAvailability = availability - qty
        products += p -> newAvailability
        (p, qty)
      }
      case _ if qty > availability => {
        products += p -> 0
        (p, availability)
      }
    }
  }
}

class Shopping(private val warehouse: Warehouse,
               private val catalog: Catalog,
               private var cart: Cart,
               private val logger: Logger) {
  def pick(p: Product, qty: Int): Cart = {
    assert(qty>0) // precondition

    logger.log(s"Trying to pick $qty pieces of $p.")
    val (_, howMany) = warehouse.get(p, qty)
    if(howMany>0) {
      logger.log(s"The warehouse has $howMany pieces; adding them to cart.")
      val price = catalog.priceFor(p, howMany)
      logger.log(s"$howMany pieces of $p collectively cost $price.")
      val item = Item(p, ItemDetails(howMany, price))
      cart.add(item)
      logger.log(s"Updated cart: now it contains ${cart.size} items for total ${cart.totalCost}")
    } else{
      logger.log("There are no pieces of the requested product in the warehouse.")
    }
    cart
  }
}

object TryShopping extends App {
  val (p1,p2) = (Product("Shoe"), Product("Hat"))
  val warehouse = new BasicWarehouse
  warehouse.supply(p1, 2)
  warehouse.supply(p2, 50)
  val catalog = new BasicCatalog(Map[Product,Price](
    p1 -> Price(78),
    p2 -> Price(34)
  ))
  val cart = new BasicCart()
  val shopping = new Shopping(warehouse, catalog, cart, new BasicLogger(">> "))
  shopping.pick(p1,1)
  shopping.pick(p2,2)
  shopping.pick(p1,2)
  println(cart.content)
  println("Total cost: " + cart.totalCost)
}