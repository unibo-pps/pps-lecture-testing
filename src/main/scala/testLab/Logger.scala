package testLab

trait Logger:
  def log(msg: String): Unit

class BasicLogger(init: String = "") extends Logger:
  override def log(msg: String): Unit = println(init + msg)


object NullLogger extends Logger:
  override def log(msg: String): Unit = ()

object LoggerMain:
  @main def main() = new BasicLogger("> ").log("Some log")
