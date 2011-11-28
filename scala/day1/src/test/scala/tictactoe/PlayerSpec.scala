package tictactoe

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends Spec with ShouldMatchers {
  val player = new Player('0')
  
  describe("A player starting with an empty grid") {
    it("should populate the first cell") {
      player.play(new TicTacToeBoard()).toString() should be ("""
0| | 
-----
 | | 
-----
 | | """)
    }
  }
  
  describe("A player playing with a row which can be completed") {
    val state = List('X','0',' ',
                     '0','0',' ',
                     'X','X','0')
    val board = new TicTacToeBoard(state)
    it("should make the winning move, ignoring preceeding rows") {
      val newState = player.play(board)
      newState.toString() should be ("""
X|0| 
-----
0|0|0
-----
X|X|0""")
      newState.wonBy() should be (Some('0'))
    }
  }
  
  describe("A player playing with a column which can be completed"){
    val state = List(' ','0',' ',
                     'X','0',' ',
                     ' ',' ','X')
    val board = new TicTacToeBoard(state)
    it("should make the winning move, ignoring preceeding columns") {
      val newState = player.play(board)
      newState.toString() should be ("""
 |0| 
-----
X|0| 
-----
 |0|X""")
      newState.wonBy() should be (Some('0'))
    }
  }
  
  describe("A player playing with a diagonal which can be completed") {
    val state = List(' ','X',' ',
                     'X','0',' ',
                     '0',' ','X')
    val board = new TicTacToeBoard(state)
    it("should make the winning move, ignoring the other diagnonal") {
      val newState = player.play(board)
      newState.toString() should be ("""
 |X|0
-----
X|0| 
-----
0| |X""")
      newState.wonBy() should be (Some('0'))
    }
  }
}