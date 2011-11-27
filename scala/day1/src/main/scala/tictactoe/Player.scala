package tictactoe

object Player {
  implicit def listWrapper(cells: List[Char]) = new TicTacToeList(cells)
}

import TicTacToeBoard._
import Player._
class Player(symbol: Char) {
  
  case class Attempt(val success: Boolean, result: List[Char]) {
    def failed : Boolean = !success
  }

  def play(board: TicTacToeBoard): TicTacToeBoard = {
    var lastAttempt = winByRows(board)
    
    if(lastAttempt.failed) {
        lastAttempt = fillFirstEmptyCell(board)
    }
    
    new TicTacToeBoard(lastAttempt.result)
  }
  
  private def winByRows(board: TicTacToeBoard): Attempt = {
    val rows = board.rows
    val attempts = rows.map { row =>
      tryToComplete(row)
    }
 
    // This is a bit too brute force, is there a list processing approach?
    attempts match {
      case List(Attempt(true,result),_,_) => Attempt(true, result  ::: rows(1) ::: rows(2))
      case List(_,Attempt(true,result),_) => Attempt(true, rows(0) ::: result  ::: rows(2))
      case List(_,_,Attempt(true,result)) => Attempt(true, rows(0) ::: rows(1) ::: result)
      case List(_,_,_) => Attempt(false, rows(0) ::: rows(1) ::: rows(2))
    }
  }
  
  private def tryToComplete(cells: List[Char]): Attempt = {
    if((cells.haveABlank) && (cells.countOf(this.symbol) == 2)) {
      Attempt(true, fillAtFirstBlank(cells))
    }
    else Attempt(false, cells)
  }
  
  private def fillFirstEmptyCell(board: TicTacToeBoard): Attempt = {
    val cells = board.rows.flatten
    if(cells.haveABlank) Attempt(true, fillAtFirstBlank(cells))
    else Attempt(false, cells)
  }
  
  private def fillAtFirstBlank(cells: List[Char]): List[Char] = {
     val (head, remainder) = cells.splitAt(cells.indexOf(TicTacToeBoard.Blank))
     (head ::: this.symbol :: remainder.tail) // Substitute the blank for whatever my symbol is
  }
  
}