# frozen_string_literal: true

require 'opal'
require 'native'

class Application
  attr_reader :scene

  def initialize(options = {})
    @app = Native(`new PIXI.Application(#{options.to_n})`)
    body = Native(`document.body`)
    body.appendChild(@app.view)

    @scene = Factory.container
    @app.stage.addChild(@scene)
  end

  def run(&block)
    @app.ticker.add(block)
  end
end

module Factory
  class << self
    def container
      container = Native(`new PIXI.Container()`)

      def container.<<(child)
        addChild(child)
      end

      container
    end

    def sprite(filename, x: 0, y: 0)
      texture = load_texture(filename)
      sprite = Native(`new PIXI.Sprite(texture)`)
      sprite.x = x
      sprite.y = y

      sprite
    end

    def load_texture(filename)
      url = "/static/images/#{filename}"
      @textures ||= {}
      @textures[filename] ||= `PIXI.Texture.from(#{url.to_n})`
    end
  end
end
