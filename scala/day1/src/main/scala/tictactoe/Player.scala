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
    
    if(lastAttempt.failed) lastAttempt = winByColumns(board)
    if(lastAttempt.failed) lastAttempt = winByDiagonals(board)
    if(lastAttempt.failed) lastAttempt = fillFirstEmptyCell(board)
    
    new TicTacToeBoard(lastAttempt.result)
  }
  
  private def winByDiagonals(board: TicTacToeBoard): Attempt = {
    val diagonals = board.diagonals
    val rows = board.rows
    val attempts = tryToWinByGroups(diagonals)
    attempts match {
      case List(Attempt(true, result),_) => Attempt(true, List(
          result(0),rows(0)(1),rows(0)(2),
          rows(1)(0),result(1),rows(1)(2),
          rows(2)(0),rows(2)(1),result(2)))
      case List(_,Attempt(true, result)) => Attempt(true, List(
          rows(0)(0),rows(0)(1),result(0),
          rows(1)(0),result(1),rows(1)(2),
          result(2),rows(2)(1),rows(2)(2)))
      case _ => Attempt(false, rows.flatten)
    }
  }
  
  
  private def winByColumns(board: TicTacToeBoard): Attempt = {
    val groups = board.columns
    val attempts = tryToWinByGroups(groups)
    
    // This is a bit too brute force, is there a list processing approach?
    attempts match {
      case List(Attempt(true,result),_,_) => Attempt(true, List(result, groups(1), groups(2)).transpose.flatten)
      case List(_,Attempt(true,result),_) => Attempt(true, List(groups(0), result, groups(2)).transpose.flatten)
      case List(_,_,Attempt(true,result)) => Attempt(true, List(groups(0), groups(1), result).transpose.flatten)
      case _ => Attempt(false, List(groups(0), groups(1), groups(2)).transpose.flatten)
    }
  }
  
  private def winByRows(board: TicTacToeBoard): Attempt = {
    val groups = board.rows
    val attempts = tryToWinByGroups(groups)
    
    // This is a bit too brute force, is there a list processing approach?
    attempts match {
      case List(Attempt(true,result),_,_) => Attempt(true, result  ::: groups(1) ::: groups(2))
      case List(_,Attempt(true,result),_) => Attempt(true, groups(0) ::: result  ::: groups(2))
      case List(_,_,Attempt(true,result)) => Attempt(true, groups(0) ::: groups(1) ::: result)
      case _ => Attempt(false, groups(0) ::: groups(1) ::: groups(2))
    }
  }
  
  private def tryToWinByGroups(groups: List[List[Char]]): List[Attempt] = {
    groups.map { group =>
      tryToComplete(group)
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