package day3

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SizerSpec extends Spec with ShouldMatchers with Sizer {

  describe("A sizer") {
    it("should run faster concurrently than sequentially") {
      (timeMethod { getPageSizeSequentially } > timeMethod { getPageSizeConcurrently }) should be(true)
    }
  }
}