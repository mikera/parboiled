package org.parboiled.scala

import org.testng.annotations.Test
import org.scalatest.testng.TestNGSuite
import org.testng.Assert.assertEquals
import testing.ParboiledTest

class SemanticPredicateTest extends ParboiledTest with TestNGSuite {

  class TestParser extends Parser {
    def Clause = rule { Number ~ " " ~ Number ~ " " ~ Number ~~~? (_ + _ == _) ~ Eoi ~~> (_ + _ - _) }

    def Number = rule { oneOrMore("0" - "9") ~> (_.toInt) }
  }

  type Result = Int

  val parser = new TestParser().withParseTreeBuilding()

  @Test
  def testWithContext() {
    parse(parser.Clause, "2 3 5") {
      assertEquals(parseTree,
         """|[Clause, {0}] '2 3 5'
            |  [Number, {2}] '2'
            |    [OneOrMore] '2'
            |      [0..9] '2'
            |  [' ', {2}] ' '
            |  [Number, {3}] '3'
            |    [OneOrMore, {2}] '3'
            |      [0..9, {2}] '3'
            |  [' ', {3}] ' '
            |  [Number, {5}] '5'
            |    [OneOrMore, {3}] '5'
            |      [0..9, {3}] '5'
            |  [EOI, {5}]
            |""".stripMargin)
    }
  }

}