package eslab

object Es2 extends App:

  val pos = "Positive"
  val neg = "Negative"

  def positive(x: Int): String = x match
    case x if x >= 0 => pos
    case _ => neg

  val positiveValFunctionLiteral : Int => String = (x: Int) => x match
    case x if x >= 0 => pos
    case _ => neg

  val positiveValShort = (x:Int) => x match
      case x if x >= 0 => pos
      case _ => neg

  //def neg(fun: String => Boolean) : String => Boolean = x => !fun(x)
  def neg(fun: String => Boolean): String => Boolean = !fun(_)

  val negVal: (String => Boolean) => String => Boolean = (fun: String => Boolean) => ((v: String) => !fun(v))

  val negValShort: (String => Boolean) => String => Boolean = fun => !fun(_)

  val empty : String => Boolean = _ == ""

  val notEmpty = negVal(empty) // which type of notEmpty?
  println(notEmpty("foo")) // true
  println(notEmpty("")) // false
  println(notEmpty("foo") && !notEmpty("")) // true.. a comprehensive test

  def negWithGenerics[A](fun:A => Boolean): A => Boolean = !fun(_)

  //2.4
  def nonCurry : (Int, Int, Boolean) => Boolean = (x: Int, y: Int, b: Boolean) => (x<=y)==b
  def nonCurryShort(x: Int, y: Int, b: Boolean) : Boolean = (x<=y)==b
  def nonCurryShorter:(Int, Int, Boolean) => Boolean = _ <= _ == _

  val nonCurryVal : (Int, Int, Boolean) => Boolean = (x: Int, y: Int, b: Boolean) => (x<=y)==b
  val superShortNonCurryVal: (Int, Int, Boolean) => Boolean = _<=_ == _

  def curry(x: Int)(y: Int)(b: Boolean) : Boolean = (x<=y)==b
  val curryVal: Int=>Int=>Boolean=>Boolean = x => y => b => (x<=y)==b

  //2.5
  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

  def genericCompose[A,B,C](f:B => C, g:A => B) : A => C = x => f(g(x))

  val sub: (Int, Int) => Int = _ - _
  val mul: (Int, Int) => Int = _ * _
  println(genericCompose(sub(_, 1), mul(_, 2))(5)) //ok, 9
  println(genericCompose[Int, Int, Int](_ - 1, _ * 2)(5)) //ok, 9

  //2.6
  def composeThree[A,B,C,D](f: C => D, g: B => C, h: A => B): A => D = x => f(g(h(x)))
  println(composeThree[Int, Int, String, String](_ + "!", _.toString, _ * 2)(3))//ok
  //Can you implement this by reusing your generic compose function?
  def h: Int => Int = _ * 2
  def g: Int => String = _.toString
  def f: String => String = _ concat "!"
  //f(g(h(x)))
  println(genericCompose(f, genericCompose(g,h))(3))//ok

  //TASK 3
  
