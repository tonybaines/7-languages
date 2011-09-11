#!/usr/bin/ruby

answer = rand(10)
wrong = true
puts "Guess > "
while (true) do
  guess = gets.to_i
  puts "Correct!"; break if guess == answer
  puts "Too high\n" if guess > answer
  puts "Too low\n" if guess < answer
  puts "Guess again > "
end
