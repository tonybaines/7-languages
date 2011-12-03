package day2

trait MapFromFile {
  def translationsFromFile(filename: String): Map[String, String] = {
    val translations = (io.Source.fromFile(filename).getLines).map { line =>
      val tokens = line.split("->")
      (tokens(0).trim, tokens(1).trim)
    }
    
    Map.empty[String,String] ++ translations
  }
}