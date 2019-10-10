# frozen_string_literal: true

require 'opal'
require 'native'

# Run JavaScript

text = 'Hello, world!'

`console.log(text)`

url = `location.href`

puts url

# DOM

element = Native(`document.createElement('div')`)
element.innerText = 'Hi!'

body = Native(`document.body`)
body.appendChild(element)

# Create PixiJS Application

options = {
  width: 500,
  height: 500,
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

# Create Sprite

sprite = Native(`new PIXI.Sprite(texture)`)
sprite.x = 200
sprite.y = 200

stage.addChild(sprite)

# Main Loop

application.ticker.add do |_delta|
  sprite.x += rand(-20..20) * 0.1
  sprite.y += rand(-20..20) * 0.1
end
