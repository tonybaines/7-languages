#!/usr/bin/ruby

my_file = File.new("input.txt", "r") # or 'w' (write only) or 'r+' (read/write)
my_file.each do |line|
  puts line
end
my_file.close

File.open("input.txt", "r") do |my_file|
  my_file.each do |line|
    puts line
  end
end # implicit close
