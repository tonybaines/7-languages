package tictactoe

object Player {
  implicit def listWrapper(cells: List[Char]) = new TicTacToeList(cells)
}

import TicTacToeBoard._
import Player._
class Player(symbol: Char) {
  
  case class Attempt(val wasASuccess: Boolean, result: List[Char]) 

  def play(board: TicTacToeBoard): TicTacToeBoard = {
    val strategies = List(winByRows _, winByColumns _, winByDiagonals _, fillFirstEmptyCell _)
    val move = firstSuccessfulAttempt(board.cells, strategies)
    new TicTacToeBoard(move.result)
  }
  
  private def winByRows(cells: List[Char]): Attempt = {
    firstSuccessfulAttempt(cells,
        List(cellGroupStrategy(List(0,1,2)), 
             cellGroupStrategy(List(3,4,5)),
             cellGroupStrategy(List(6,7,8))
        )
    )
  }
  
  private def winByColumns(cells: List[Char]): Attempt = {
    firstSuccessfulAttempt(cells,
        List(cellGroupStrategy(List(0,3,6)), 
             cellGroupStrategy(List(1,4,7)),
             cellGroupStrategy(List(2,5,8))
        )
    )
  }
  
  private def winByDiagonals(cells: List[Char]): Attempt = {
    firstSuccessfulAttempt(cells,
        List(cellGroupStrategy(List(0,4,8)), 
             cellGroupStrategy(List(2,4,6))))
  }
  
  /*
   * Boring fall-back strategy
   */
  private def fillFirstEmptyCell(cells: List[Char]): Attempt = {
    if(cells.haveABlank) Attempt(true, fillAtFirstBlank(cells))
    else Attempt(false, cells)
  }
  
  /*
   * Find a winning strategy from the supplied list, 
   * return a successful Attempt or try the next strategy
   */
  private def firstSuccessfulAttempt(cells: List[Char], strategies: List[(List[Char] => Attempt)]): Attempt = {
    val outcome = strategies(0)(cells)
    if (outcome.wasASuccess) outcome
    else if(strategies.tail.isEmpty) Attempt(false, outcome.result)
    else firstSuccessfulAttempt(cells, strategies.tail)
  } 
  
  /*
   * Strategy suitable for currying to allow use in the firstSuccessfulAttempt(...) traversal
   */
  private def cellGroupStrategy(group: List[Int])(cells: List[Char]): Attempt = {
    if(winnableFrom(List(cells(group(0)), cells(group(1)), cells(group(2))))) 
      new Attempt(true, winWith(group, cells)) 
    else 
      new Attempt(false, cells)
  }
  
  private def winnableFrom(values: List[Char]): Boolean = (values.haveABlank) && (values.countOf(this.symbol) == 2)
  
  private def winWith(group: List[Int], cells: List[Char]): List[Char] = {
    (cells /: group) {(grid, index) => grid.updated(index, this.symbol)}
  }
  
  private def fillAtFirstBlank(cells: List[Char]): List[Char] = {
     val (head, remainder) = cells.splitAt(cells.indexOf(TicTacToeBoard.Blank))
     (head ::: this.symbol :: remainder.tail) // Substitute the blank for whatever my symbol is
  }  
}