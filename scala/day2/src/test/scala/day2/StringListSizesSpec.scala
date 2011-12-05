package day2

import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import StringListSizes._

@RunWith(classOf[JUnitRunner])
class StringListSizesSpec extends FunSuite with ShouldMatchers {
  test("the size of a List with a single word") {
    totalSizeOf(List("fred")) should be (4)
  }
  
  test("the size of a List with a two words") {
    totalSizeOf(List("fred", "barney")) should be (10)
  }
}