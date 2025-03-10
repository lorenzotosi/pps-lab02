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