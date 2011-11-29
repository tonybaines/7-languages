package tictactoe

class Game(player1: Player, player2: Player) {
  var board = new TicTacToeBoard()
  
  def playRound() = {
    board = player1.play(board)
    board = player2.play(board)
  }
  
  def winner: Option[Player] = {
    None
  }
  
  def roundsPlayedBy(player: Player) = 1
}