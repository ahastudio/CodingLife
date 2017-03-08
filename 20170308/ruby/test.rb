require 'sinatra'
require 'active_support/all'

class State
  def initialize
    @items = {}
  end

  def [](key)
    @items[key.to_sym]
  end

  def []=(key, value)
    @items[key.to_sym] = value
  end

  def method_missing(method, *args)
    name = method.id2name
    case name[-1]
    when '='
      @items[args[0]] = args[1]
    when '?'
      key = name[0...-1]
      !!@items[key.to_sym]
    else
      @items[name.to_sym]
    end
  end
end

class Condition
  def initialize(key, op, value)
    @key = key
    @op = op
    @value = value
  end

  def enough?(state)
    case @op.to_sym
    when :is
      state[@key] == @value
    when :isnt
      state[@key] != @value
    end
  end
end

class Action
  def initialize(key, op, value)
    @key = key
    @op = op
    @value = value
  end

  def run(state)
    case @op.to_sym
    when :set
      state[@key] = @value
    end
  end
end

class Scene
  attr_accessor :preference, :text

  def initialize(name)
    @name = name
    @preference = 1
    @text = ''
    @conditions = []
    @actions = []
  end

  def add_condition(key, op, value)
    @conditions << Condition.new(key, op, value)
  end

  def available?(state)
    @conditions.all? { |i| i.enough?(state) }
  end

  def add_action(key, op, value)
    @actions << Action.new(key, op, value)
  end

  def run(state, &block)
    block&.call(@text)
    @actions.each { |i| i.run(state) }
  end
end

class App
  def initialize
    @state = State.new
    load_scenes
  end

  def run(&block)
    @visits = []
    scene = @scenes.first
    loop do
      break if scene.nil?
      scene.run(@state) do |text|
        block&.call(text)
      end
      break if @state.quit?
      @visits << scene
      scene = next_scene
    end
    puts '-' * 40, @state.inspect
  end

  def main
    run do |text|
      puts text
    end
  end

  private

  def load_scenes
    @scenes = []

    data = open('data.txt').read.split("====\n").map(&:strip)
    data.each_with_index do |item, index|
      next if item.blank?
      name, condition, action, text = item.split("----\n").map(&:strip)
      scene = Scene.new(name)
      scene.preference = (100 - index) * 1.8
      scene.text = text
      condition.split("\n").each { |i| scene.add_condition(*i.split) }
      action.split("\n").each { |i| scene.add_action(*i.split) }
      # puts scene.inspect
      @scenes << scene
    end
  end

  def next_scenes
    @scenes.select { |i| i.available?(@state) } - @visits
  end

  def next_scene
    scenes = next_scenes
    return if scenes.empty?
    sum = [scenes.map(&:preference).reduce(:+).to_i, 1].max
    weights = scenes.map { |i| i.preference.to_f / sum }
    scenes.zip(weights).max_by { |_, weight| rand * weight }.first
  end
end

get '/' do
  body = '<pre>'
  App.new.run do |text|
    body << text << "\n"
  end
  body << '</pre>'
  body
end

get '/text' do
  '<pre>' << open('data.txt').read << '</pre>'
end

puts '-' * 80
App.new.main
puts '-' * 80
