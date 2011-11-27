package tictactoe

import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TicTacToeSpec extends FeatureSpec with GivenWhenThen with ShouldMatchers {
  feature("Playing a game of tic-tac-toe") {
    scenario("The start of a game, playing as crosses") {
      given("an new game and a player")
      val crosses = new Player('X')
      val noughts = new Player('0')
      val game = new Game(crosses, noughts)
      
      when("a round is played")
      game.playRound()
      
      then("the new state of the game should include a play by crosses")
      game.roundsPlayedBy(crosses) should be (1)
      and("by noughts")
      game.roundsPlayedBy(noughts) should be (1)
      
      and("there should be no winner")
      game.winner should be (None)
    }
  }
}