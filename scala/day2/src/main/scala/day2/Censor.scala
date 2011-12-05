package day2

trait Censor {
  def purify(words: List[String], translated: Map[String, String]): List[String] = {
    words.map { word =>
      if (translated.contains(word)) translated(word) else word
    }
  }
}