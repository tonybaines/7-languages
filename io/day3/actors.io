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
     
     _players first tag
   )
   
   notify := method(player,
     incrementScoreFor(player)
   )
   
   incrementScoreFor := method(player,
     writeln("Incrementing the score for " .. player name)
     scores atPut(player name, scoreFor(player)+1)
   )
   
   scoreFor := method(player,
     scores at(player name)
   )
 )
 
 Player := Object clone do (
   players := list()
   scorer := nil
   name := "anonymous"
   getReady := method(_scorer, _players,
     scorer = _scorer
     players = _players
     writeln(self name .. " Ready!")
   )
   
   tag := method (
     writeln(self name .. " tagged")
     scorer notify(self)
   )
 )
 
 
 Suite := UnitTest clone do(
  testPlayersShouldPickAnotherPlayerToTag := method(
    scorer := Scorer clone
    player1 := Player clone do (
      name = "Player 1"
    )
    player2 := Player clone do (
      name = "Player 2"
    )
    players := list(player1,player2)
    player1 getReady(scorer, players)
    player2 getReady(scorer, players)
    scorer startGame(players, 1)
    
    assertEquals(0, scorer scoreFor(player2))
    assertEquals(1, scorer scoreFor(player1))
  )
 )
 
 Suite run
