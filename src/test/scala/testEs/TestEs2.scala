package testEs

import org.junit.*
import org.junit.Assert.*
import eslab.Es2.*
import org.junit.jupiter.api.Assertions.assertAll

class TestEs2:

  @Test def testVal(): Unit = {
    assertEquals(positiveValFunctionLiteral(5), pos)
  }

  @Test def testValNeg(): Unit = {
    assertEquals(positiveValFunctionLiteral(-5), neg)
  }

  @Test def testValFail(): Unit = {
    assertNotEquals(positiveValFunctionLiteral(-5), pos)
  }

  @Test def testDef(): Unit = {
    assertEquals(positive(2), pos)
  }

  @Test def testDefNeg(): Unit = {
    assertEquals(positive(-2), neg)
  }

  @Test def testDefFail(): Unit = {
    assertNotEquals(positive(-2), pos)
  }

  @Test def testNegWithGenerics(): Unit = {
    val empty : String => Boolean = _ == ""
    val notEmpty = negWithGenerics(empty)

    assertAll(() => assertTrue(notEmpty("foo")),
      ()=> assertFalse(notEmpty("")),
      ()=> assertTrue(notEmpty("foo") && !notEmpty("")))
  }

  @Test def testNonCurryDef(): Unit = {
    assertTrue(nonCurry(1,2,true))
    assertTrue(nonCurryVal(1,2,true))
  }

  @Test def testCurryDef(): Unit = {
    assertTrue(curry(1)(2)(true))
  }

  @Test def testNonCurryVal(): Unit = {
    assertTrue(nonCurryVal(1,2,true))
  }

  @Test def testCurryVal(): Unit = {
    val x = curryVal(1)(2)(true)
    assertTrue(curryVal(1)(2)(x))
  }

  @Test def testCompose(): Unit = {
    assertEquals(9, compose(_ - 1, _ * 2)(5))
  }

  @Test def testGenericCompose(): Unit = {
    val sub:(Int, Int) => Int = _ - _
    val mul:(Int, Int) => Int = _ * _
    assertEquals(9, genericCompose(sub(_,1), mul(_,2))(5))
  }