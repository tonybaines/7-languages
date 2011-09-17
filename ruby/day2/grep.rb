#!/usr/bin/ruby

module Grepping
  class Grep
    attr :file_name
    def initialize(file_name)
      @file_name=file_name
    end
    def grep(pattern)
      linenum=0
      File.open(file_name, "r") do |the_file|
        the_file.each do |line|
          linenum+=1
          puts "#{linenum} #{line}" if line =~ pattern
        end
      end
    end
  end
end

include Grepping

if ARGV.size == 2
  grepper = Grep.new(ARGV[0])
  grepper.grep Regexp.new(ARGV[1])
else
  puts "Usage: grep.rb file_name pattern"
end
