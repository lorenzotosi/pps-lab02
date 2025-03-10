package testEs

import org.junit.*
import org.junit.Assert.*
import eslab.Es2.*

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
