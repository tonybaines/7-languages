#!/usr/bin/ruby
my_array = (1..16).to_a
count=0
my_array.each do |it|
  count+=1
  print "#{it} "
  print "\n" if count%4 == 0
end

my_array.each_slice(4) do |it|
  p it # what is the difference between 'p' and puts/print ?
# print - print it
# puts - to_s and trim
# p - inspect()
end
