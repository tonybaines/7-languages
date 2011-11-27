package tictactoe

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TicTacToeBoardSpec extends Spec with ShouldMatchers {
  describe("A new tic-tac-toe board") {
    it("should start out empty") {
      new TicTacToeBoard().toString() should be("\n" +
        " | | \n" +
        "-----\n" +
        " | | \n" +
        "-----\n" +
        " | | ")
    }
    
    it("should have three empty rows") {
      val rows = new TicTacToeBoard().rows
      rows.size should be (3)
      rows.foreach { row =>
        row should be (List(' ',' ',' '))
      }
    }
    
    it("should have three empty columns") {
      val columns = new TicTacToeBoard().columns
      columns.size should be (3)
      columns.foreach { col =>
        col should be (List(' ',' ',' '))
      }
    }
    
    it("should have two empty diagonals") {
      val diagonals = new TicTacToeBoard().diagonals
      diagonals.size should be (2)
      diagonals.foreach { diagonal =>
        diagonal should be (List(' ',' ',' '))
      }
    }
    
    it("should have no winner") {
      new TicTacToeBoard().wonBy() should be (None)
    }
  }
  
  describe("A tic-tac-toe board which has been created from a winning state") {
      val state = List('X','0','0',
                       'X','0','X',
                       'X','X','0')
      val board = new TicTacToeBoard(state)
      
    it("should be pretty-printed") {
      board.toString() should be("""
X|0|0
-----
X|0|X
-----
X|X|0""")
    }
    
    it("should have filled rows") {
      board.rows(0) should be (List('X','0','0'))
      board.rows(1) should be (List('X','0','X'))
      board.rows(2) should be (List('X','X','0'))
    }
    
    it("should have filled columns") {
      board.columns(0) should be (List('X','X','X'))
      board.columns(1) should be (List('0','0','X'))
      board.columns(2) should be (List('0','X','0'))
    }
    
    it("should have filled diagonals") {
      board.diagonals(0) should be (List('X','0','0'))
      board.diagonals(1) should be (List('0','0','X'))
    }
    
    it("should have been won by 'X'") {
      board.wonBy() should be (Some('X'))
    }
  }
  
  describe("a tic-tac-toe board that has been initialised with a winning column") {
    val state = List('X','0','X',
                     'X','0','0',
                     '0','0','X')
    val board = new TicTacToeBoard(state)
    
    it("should have been won by '0'") {
      board.wonBy() should be(Some('0'))
    }
  }
  
  describe("a tic-tac-toe board that has been initialised with a winning diagonal") {
    val state = List('X','X','0',
                     'X','0','X',
                     '0','0','X')
    val board = new TicTacToeBoard(state)
    
    it("should have been won by '0'") {
      board.wonBy() should be(Some('0'))
    }
  }

}