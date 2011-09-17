#!/usr/bin/ruby
# ruby 1.8.7 (2010-08-16 patchlevel 302) [x86_64-linux]
puts "Hello, world"
# Hello, world
# => nil

"Hello, Ruby".index 'Ruby' 
# => 7

(1..10).each {|i| puts "This is sentence number #{i}"}
# This is sentence number 1
# This is sentence number 2
# This is sentence number 3
# This is sentence number 4
# This is sentence number 5
# This is sentence number 6
# This is sentence number 7
# This is sentence number 8
# This is sentence number 9
# This is sentence number 10
# => 1..10

10.times do
  puts "Tony\n"
end
# Tony
# Tony
# Tony
# Tony
# Tony
# Tony
# Tony
# Tony
# Tony
# Tony
# => 10
