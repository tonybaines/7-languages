package day2

object StringListSizes {
  def totalSizeOf(strings: List[String]): Int = {
    (0 /: strings) {(sum, it) => sum + it.length}
  }
}