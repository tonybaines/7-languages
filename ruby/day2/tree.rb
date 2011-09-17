#!/usr/bin/ruby

class Tree
  attr_accessor :children, :node_name

  def initialize(name, children=[])
    @children = children
    @node_name = name
  end

  def self.from_hash(structure)
    tree = structure.collect do |name,children|
      child_nodes = []
      unless children.nil?
        child_nodes << from_hash(children)
      end
      Tree.new(name,child_nodes.flatten)
    end
    tree.size > 1 ? tree : tree[0]
  end

  def visit_all(&block)
    visit &block
    children.each {|c| c.visit_all &block}
  end

  def visit(&block)
    block.call self
  end
end

family_tree = Tree.from_hash({'top' => {'level1.1' => {}, 'level1.2' => {}}})
family_tree.visit_all {|node| puts node.node_name}

ruby_tree = Tree.new( "Ruby" ,
[Tree.new("Reia" ),
Tree.new("MacRuby" )] )

puts "Visiting a node"
ruby_tree.visit {|node| puts node.node_name}

puts
puts "visiting entire tree"
ruby_tree.visit_all {|node| puts node.node_name}

#family_tree = Tree.from_hash({ 
#'grandpa' => { 
#    'dad' => {
#      'child 1' => {}, 
#      'child 2' => {} 
#    }, 
#    'uncle' => {
#      'child 3' => {}, 
#      'child 4' => {} 
#    } 
#  } 
#})
