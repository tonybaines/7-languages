package tictactoe

class TicTacToeBoard(board: List[Char]) {
  def this() = {
    this(List(' ', ' ', ' ',     
              ' ', ' ', ' ',
              ' ', ' ', ' '))
  }
  def rows: List[List[Char]] = {
    List(board.slice(0,3), board.slice(3,6), board.slice(6,9))
  }
  
  def columns: List[List[Char]] = {
    List(List(board(0), board(3), board(6)), 
         List(board(1), board(4), board(7)), 
         List(board(2), board(5), board(8)))
  }
  
  def diagonals: List[List[Char]] = {
    List(List(board(0), board(4), board(8)),
         List(board(2), board(4), board(6)))
  }
  
  private def winningLinesIn(lines: List[List[Char]]): List[List[Char]] = {
    lines.filter { it =>
      it.distinct.size == 1 && it.distinct(0) == it(0) && it(0) != ' '
    }
  }
  
  def wonBy(): Option[Char] = {
    val winningRow = winningLinesIn(rows)
    val winningCol = winningLinesIn(columns)
    val winningDiagonal = winningLinesIn(diagonals)
    
    if(winningRow.size > 0) {
      Some(winningRow(0)(0))
    }
    else if (winningCol.size > 0) {
      Some(winningCol(0)(0))
    }
    else if (winningDiagonal.size > 0) {
      Some(winningDiagonal(0)(0))
    }
    else None
  }
  
  override def toString(): String = {
    """
%c|%c|%c
-----
%c|%c|%c
-----
%c|%c|%c""".format(board(0),board(1),board(2),
                   board(3),board(4),board(5),
                   board(6),board(7),board(8))
  }
}