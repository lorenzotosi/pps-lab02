package eslab

import eslab.Es2.Expr.*

import scala.annotation.tailrec

object Es2 extends App:
//Task 2.3 a
  val pos = "Positive"
  val neg = "Negative"

  def positive(x: Int): String = x match
    case x if x >= 0 => pos
    case _ => neg

  val positiveValFunctionLiteral: Int => String = (x: Int) => x match
    case x if x >= 0 => pos
    case _ => neg

  val positiveValShort = (x: Int) => x match
      case x if x >= 0 => pos
      case _ => neg
  //Task 2.3 b

  //def neg(fun: String => Boolean) : String => Boolean = x => !fun(x)
  def neg(fun: String => Boolean): String => Boolean = !fun(_)

  val negVal: (String => Boolean) => String => Boolean = (fun: String => Boolean) => (v: String) => !fun(v)

  val negValShort: (String => Boolean) => String => Boolean = fun => !fun(_)

  val empty : String => Boolean = _ == ""
  val notEmpty = negVal(empty) // which type of notEmpty?
  println(notEmpty("foo")) // true
  println(notEmpty("")) // false
  println(notEmpty("foo") && !notEmpty("")) // true.. a comprehensive test

  //Task 2.3 c
  def negWithGenerics[A](fun: A => Boolean): A => Boolean = !fun(_)

  //2.4
  def nonCurry : (Int, Int, Boolean) => Boolean = (x: Int, y: Int, b: Boolean) => (x <= y) == b
  def nonCurryShort(x: Int, y: Int, b: Boolean) : Boolean = (x <= y) == b
  def nonCurryShorter:(Int, Int, Boolean) => Boolean = _ <= _ == _
  val nonCurryVal : (Int, Int, Boolean) => Boolean = (x: Int, y: Int, b: Boolean) => (x <= y) == b
  val nonCurryValSuperShort: (Int, Int, Boolean) => Boolean = _ <= _ == _

  def curry(x: Int)(y: Int)(b: Boolean) : Boolean = (x<=y)==b
  val curryVal: Int => Int => Boolean => Boolean = x => y => b => (x <= y) == b

  //2.5
  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

  def genericCompose[A,B,C](f: B => C, g: A => B) : A => C = x => f(g(x))

  val sub: (Int, Int) => Int = _ - _
  val mul: (Int, Int) => Int = _ * _
  println(genericCompose(sub(_, 1), mul(_, 2))(5)) //ok, 9
  println(genericCompose[Int, Int, Int](_ - 1, _ * 2)(5)) //ok, 9

  //2.6
  def composeThree[A,B,C,D](f: C => D, g: B => C, h: A => B): A => D = x => f(g(h(x)))
  println(composeThree[Int, Int, String, String](_ + "!", _.toString, _ * 2)(3))//ok
  //Can you implement this by reusing your generic compose function?
  //f(g(h(x)))
  def h: Int => Int = _ * 2
  def g: Int => String = _.toString
  def f: String => String = _ concat "!"
  genericCompose(f, genericCompose(g, h))(3)//ok

  //TASK 3
  //3.7
  def power(base: Double, exponent: Int): Double = exponent match
    case i if exponent > 0 => base * power(base, exponent - 1)
    case 0 => 1

  def powerTail(base: Double, exponent: Int): Double =
    @annotation.tailrec
    def _power(b: Double, exp: Int): Double = exp match
      case i if exp > 0 => _power(base * b, exp - 1)
      case 0 => b
    _power(1, exponent)

  //3.8
  val getLastDigit : Int => Int = _ % 10
  val removeLastDigit : Int => Int = _ / 10
  val addDigitToTail : (Int, Int) => Int = _ * 10 + _

  def reverseNumber(n: Int): Int =
    @annotation.tailrec
    def _builder(acc: Int, current: Int, numb: Int): Int = acc match
      case 0 => current
      case _ =>
        val x = getLastDigit(numb)
        val y = removeLastDigit(numb)
        _builder(acc -1, addDigitToTail(current, x), y)
    _builder(n.toString.length, 0, n)

//TASK 4
  enum Expr:
    case Literal(x: Int)
    case Add(x: Expr, y: Expr)
    case Multiply(x: Expr, y: Expr)

  object Operations:
    def evaluate(expr: Expr): Int = expr match
      case Literal(n) => n
      case Add(x, y) => evaluate(x) + evaluate(y)
      case Multiply(x, y) => evaluate(x) * evaluate(y)
    def show(expr: Expr): String = expr match
      case Literal(n) => n.toString
      case Add(x, y) => "(" concat show(x) concat " + " concat show(y) concat ")"
      case Multiply(x, y) => "(" concat show(x) concat " * " concat show(y) concat ")"

//Task 5,
// ho copiato dall'altro file la map e la filter che ho implementato, le ho commentate così non ho errori qui

//  def map[A, B](optional: Optional[A], f: A => B): Optional[B] = optional match
//    case Maybe(value) => Maybe(f(value))
//    case _ => Empty()
//
//  def filter[A, B](optional: Optional[A], f: A => B): Optional[A] = optional match
//    case Maybe(value) if f(value) == true => Maybe(value)
//    case _ => Empty()