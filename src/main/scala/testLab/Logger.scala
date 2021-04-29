package testLab

trait Logger {
  def log(msg: String)
}

class BasicLogger(init: String = "") extends Logger {
  override def log(msg: String): Unit = println(init + msg)
}

object NullLogger extends Logger {
  override def log(msg: String): Unit = ()
}

object LoggerMain extends App {
  new BasicLogger("> ").log("Some log")
}