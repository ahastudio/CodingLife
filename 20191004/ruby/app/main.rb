# frozen_string_literal: true

require 'application'

class Bunny
  def initialize(scene)
    @sprite = Factory.sprite('bunny.png')
    scene << @sprite

    @sprite.x = rand(0...500 - 25)
    @sprite.y = rand(0...500 - 35)

    @vx = rand(-30..30).to_f / 10
    @vy = rand(-30..30).to_f / 10
  end

  def update
    @sprite.x += @vx
    @sprite.y += @vy

    @vx *= -1 if @sprite.x.negative? || @sprite.x >= 500 - 25
    @vy *= -1 if @sprite.y.negative? || @sprite.y >= 500 - 35
  end
end

app = Application.new(
  width: 500,
  height: 500,
  backgroundColor: '0x1099BB'
)

scene = app.scene

bunnies = (0...50).map { Bunny.new(scene) }

app.run do
  bunnies.each(&:update)
end
