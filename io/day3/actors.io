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
   startGame := method ( _players, _numberOfRounds,
     "Go!" println
     players := _players
     numberOfRounds := _numberOfRounds
     
   )
   
   scoreFor := method(
     0
   )
 )
 
 Player := Object clone do (
   name := "anonymous"
   getReady := method(scorer, players,
     writeln(self name .. " Ready!")
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
    
    assertEquals(1, scorer scoreFor(player2))
    assertEquals(0, scorer scoreFor(player1))
  )
 )
 
 Suite run
