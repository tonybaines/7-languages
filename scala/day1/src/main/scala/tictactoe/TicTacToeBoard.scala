package tictactoe


object TicTacToeBoard {
  val Blank = ' '
}

import TicTacToeBoard._
class TicTacToeBoard(val cells: List[Char]) {
  def this() = {
    this(List(Blank, Blank, Blank,     
              Blank, Blank, Blank,
              Blank, Blank, Blank))
  }
  
  def wonBy(): Option[Char] = {
    val winningLines = List(rows, columns, diagonals).map { lineGroup =>
      val winners = winningLinesIn(lineGroup) 
      if(winners.nonEmpty) winners else Nil
    }.flatten.flatten // flatten down to a list of characters, only winning lines remain
    
    if(winningLines.nonEmpty) Some(winningLines(0)) else  None
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
  
  private def rows: List[List[Char]] = {
    List(cells.slice(0,3), cells.slice(3,6), cells.slice(6,9))
  }
  
  private def columns: List[List[Char]] = {
    List(List(cells(0), cells(3), cells(6)), 
         List(cells(1), cells(4), cells(7)), 
         List(cells(2), cells(5), cells(8)))
  }
  
  private def diagonals: List[List[Char]] = {
    List(List(cells(0), cells(4), cells(8)),
         List(cells(2), cells(4), cells(6)))
  }
  
  private def winningLinesIn(lines: List[List[Char]]): List[List[Char]] = {
    lines.filter { it =>
      it.distinct.size == 1 && // Just one character fills the collection
      it(0) != Blank  // ignore all blanks
    }
  }
}