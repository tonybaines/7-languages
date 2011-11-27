package tictactoe


object TicTacToeBoard {
  val Blank = ' '
}

import TicTacToeBoard._
class TicTacToeBoard(cells: List[Char]) {
  def this() = {
    this(List(Blank, Blank, Blank,     
              Blank, Blank, Blank,
              Blank, Blank, Blank))
  }
  def rows: List[List[Char]] = {
    List(cells.slice(0,3), cells.slice(3,6), cells.slice(6,9))
  }
  
  def columns: List[List[Char]] = {
    List(List(cells(0), cells(3), cells(6)), 
         List(cells(1), cells(4), cells(7)), 
         List(cells(2), cells(5), cells(8)))
  }
  
  def diagonals: List[List[Char]] = {
    List(List(cells(0), cells(4), cells(8)),
         List(cells(2), cells(4), cells(6)))
  }
  
  private def winningLinesIn(lines: List[List[Char]]): List[List[Char]] = {
    lines.filter { it =>
      it.distinct.size == 1 && // Just one character fills the collection
      it(0) != Blank  // ignore all blanks
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
%c|%c|%c""".format(cells(0),cells(1),cells(2),
                   cells(3),cells(4),cells(5),
                   cells(6),cells(7),cells(8))
  }
}