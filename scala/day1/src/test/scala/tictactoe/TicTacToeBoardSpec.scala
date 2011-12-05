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