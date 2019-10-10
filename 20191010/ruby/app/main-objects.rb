# frozen_string_literal: true

require 'opal'
require 'native'

SCREEN_WIDTH = 500
SCREEN_HEIGHT = 500

# Create PixiJS Application

options = {
  width: SCREEN_WIDTH,
  height: SCREEN_HEIGHT,
  backgroundColor: '0x1099BB'
}

application = Native(`new PIXI.Application(#{options.to_n})`)

# Attach View

Native(`document.body`).appendChild(application.view)

# Get Stage

stage = application.stage

# Create Texture

url = '/static/images/bunny.png'
texture = `PIXI.Texture.from(url)`

# Game Object

class GameObject
  def initialize(stage, texture)
    @sprite = create_sprite(texture)

    stage.addChild(@sprite)

    @vx = rand(-100..100) * 0.01 * 5
    @vy = rand(-100..100) * 0.01 * 5
  end

  def create_sprite(_texture)
    sprite = Native(`new PIXI.Sprite(texture)`)
    sprite.x = 200
    sprite.y = 200
    sprite
  end

  def update
    @sprite.x += @vx
    @sprite.y += @vy

    @vx *= -1 if left.negative? || right > SCREEN_WIDTH
    @vy *= -1 if top.negative? || bottom > SCREEN_HEIGHT
  end

  def left
    @sprite.x
  end

  def right
    @sprite.x + @sprite.width
  end

  def top
    @sprite.y
  end

  def bottom
    @sprite.y + @sprite.height
  end
end

# Create Objects

objects = []

50.times do
  objects << GameObject.new(stage, texture)
end

# Main Loop

application.ticker.add do
  objects.each(&:update)
end
