package day2
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CensorSpec extends Spec with ShouldMatchers with Censor with MapFromFile {
  val translations = translationsFromFile("translations.map")
  
  describe("Building a Map from a file") {
    it("should read a file of the form 'key -> value' and return a Map") {
      translations("Shoot") should be("Pucky") 
      translations("Darn") should be("Beans") 
    }
  }

  describe("A censor with a strict sense of propriety") {
    it("should correct a list of Strings with one, safe, word by doing nothing") {
      purify(List("Gosh"), translations) should be (List("Gosh"))
    }
    
    it("should correct a list of Strings with one banned word") {
      purify(List("Shoot"), translations) should be (List("Pucky"))
    }
    
    it("should correct a list of Strings with one banned word and one safe word") {
      purify(List("Shoot", "Gosh"), translations) should be (List("Pucky", "Gosh"))
    }
    
    it("should correct a list of Strings with several banned and safe words") {
      purify(List("Shoot", "Gosh", "Darn", "Shoot", "Trouser"), translations) should be (List("Pucky", "Gosh", "Beans", "Pucky", "Trouser"))
    }
  }
}