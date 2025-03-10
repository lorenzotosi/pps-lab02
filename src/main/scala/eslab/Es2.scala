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