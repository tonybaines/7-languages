#!/usr/local/bin/io

/*
 * Actors example;
 * A simple game with 2 or more players and a scorer
 *  + The Scorer nominates a player at random
 *  + The player chooses another player at random
 *     + They shout the name of the player to the Scorer
 *     + They call 'tag' to that player
 *  + Repeat ...
 *  + The game stops when the Scorer says that a 
 *    number of turns has been had
 *
 */
 
 Scorer := Object clone do (
   numberOfRounds := 0
   scores := Map clone
   playersByName := Map clone
   startGame := method ( _players, _numberOfRounds,
     "Go!" println
     _players foreach(player, 
       playersByName atPut(player name, player)
       scores atPut(player name, 0)
     )
     numberOfRounds = _numberOfRounds
     
     (_players at(Random value((_players size)) floor())) @@tag // Start the game with an async call to the first player 
   )
   
   theWinnerIs := method(
     waitUntilDone
     highScore := scores values max
     scores foreach(player, score,
       if(score == highScore, return player)
     )
   )
   
   notify := method(player,
     if(gameOver,
        writeln("No more scores; game-over"),
        scores atPut(player name, scoreFor(player)+1)
     )
   )
   
   gameOver := method(numberOfRounds <= totalScore)
   
   scoreFor := method(player,
     scores at(player name)
   )
   
   totalScore := method(scores values sum)
   
   waitUntilDone := method(
     while(gameOver != true, 
      writeln("Not done yet: " .. totalScore .. " rounds out of " .. numberOfRounds .. " played")
      wait(1) // Thread starvation otherwise
     )
     stopAllPlayers
     true
   )
   
   stopAllPlayers := method(
     playersByName values foreach(player, player stop)
     writeln("GAME OVER!")
   )
 )
 
 Player := Object clone do (
   players := list()
   scorer := nil
   name := "anonymous"
   stillGoing := false
   getReady := method(_scorer, _players,
     scorer = _scorer
     players = _players
     stillGoing = true
     writeln(self name .. " Ready!")
   )
   
   tag := method (
     writeln(self name .. " tagged")
     wait(1) // yield works just as well
     if(stillGoing,
       scorer @@notify(self) // Let the Scorer know what is going on
       nextPlayer @@tag // Send a message to the next player
     )
   )
   
   nextPlayer := method(
     next := players at(Random value((players size)) floor())
     while(next == self, next = nextPlayer) // Play nice, a player can't tag themselves
     return next
   )
   
   stop := method(stillGoing = false)
 )
 
 
Suite := UnitTest clone do(
  players := list()
  scorer := nil
  player1 := Player clone do (
    name = "Player 1"
  )
  player2 := Player clone do (
    name = "Player 2"
  )
  players := list(player1,player2)
   
  setUp = method (
    scorer = Scorer clone
    player1 getReady(scorer, players)
    player2 getReady(scorer, players)
  )
  
  testTheScorerShouldRecordWhoHasBeenTagged := method(
    scorer startGame(players, 1)
    writeln(scorer theWinnerIs .. " wins!")
    
    assertEquals(1, scorer scoreFor(player1) + scorer scoreFor(player2))
  )
  
/*  
  While this was true when running sequentially
  multi-threading races make it non-deterministic
  
  testPlayersShouldPickAnotherPlayerToTag := method(
    scorer startGame(players, 2)
    writeln(scorer @theWinnerIs .. " wins!")
    
    assertEquals(1, scorer scoreFor(player1))
    assertEquals(1, scorer scoreFor(player2))    
  )
*/  
  testTheTotalScoreForAGameShouldBeTheSameAsTheNumberOfTurns := method(
    player3 := Player clone do (
      name = "Player 3"
    )
    player4 := Player clone do (
      name = "Player 4"
    )
    
    morePlayers := players clone
    morePlayers append(player3)
    morePlayers append(player4)
                                                         
    player1 getReady(scorer, morePlayers)
    player2 getReady(scorer, morePlayers)
    player3 getReady(scorer, morePlayers)
    player4 getReady(scorer, morePlayers)
    
    scorer startGame(morePlayers, 20)
    
    writeln(scorer theWinnerIs .. " wins!")
    
    assertEquals(20, scorer totalScore)
  )
)
 
Suite run
