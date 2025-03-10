package eslab

object Es extends App:

  // standard function with no currying
  def mult(x: Double, y: Double): Double = x * y

  val f :(Int, Int) => Int = (x: Int , y: Int) => x + y

  val multV : (Double, Double) => Double = _ * _

  val prova = (x:Int, y:Int) => x + y

  // function with currying
  // curriedMult has actually type: Double => (Double => Double)
  def curriedMult(x: Double)(y: Double): Double = x * y

  def multCurried(i: Int): Int = i * 3

  val multiplyBy3 = multCurried(3)

  println(multiplyBy3)

  def divide(x: Double)(y: Double): Double = x / y

  println(divide(10.0)(5.0))
  val a = divide(5.0)(2.0)
  println(a)

  println("--")
  def divideNoC(x:Double, y:Double) : Double = x / y

  //ok ho capito
  val b = divide(8.0)
  println(b(2.0))

  println(divideNoC(5.0, 4.0))
