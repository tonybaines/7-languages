#!/usr/bin/ruby

my_hash =  {:a => 1, :b => 2, :c => 3}
# => {:b=>2, :c=>3, :a=>1}
my_hash.keys
# => [:b, :c, :a]
my_hash.to_a
# => [[:b, 2], [:c, 3], [:a, 1]]
my_hash.values
# => [2, 3, 1]

my_hash.each_pair {|k,v| puts "#{k} is #{v}"}
# b is 2
# c is 3
# a is 1
# => {:b=>2, :c=>3, :a=>1}

