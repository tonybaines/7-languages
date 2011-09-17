#!/usr/bin/ruby

answer = rand(10)
wrong = true
puts "Guess > "
while (true) do
  guess = gets.to_i
  puts "Too high\n" if guess > answer
  puts "Too low\n" if guess < answer
  if guess == answer then
    puts "Correct !"
    break
  else
    puts "Guess again >"
  end
end
