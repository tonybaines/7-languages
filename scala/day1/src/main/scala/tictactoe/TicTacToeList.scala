package tictactoe

final class TicTacToeList(cells: List[Char]) {
  def haveABlank: Boolean = cells.contains(TicTacToeBoard.Blank)
  def countOf(c: Char) = cells.count {cell => cell == c}
}