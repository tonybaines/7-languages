package day2

trait Censor {
  def purify(words: List[String], translations: Map[String, String]): List[String] = {
    words.map { word =>
      if(translations.contains(word)) translations(word)
      else word
    }
  } 
}