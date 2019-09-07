import { Application, Texture, Container, Sprite } from 'pixi.js';

const textures = {};

const loadTexture = name => {
  if (textures[name]) {
    return textures[name];
  }

  const texture = Texture.from(`assets/${name}.png`);
  textures[name] = texture;

  return texture;
};

export function Scene({ width, height }) {
  const app = new Application({
    width,
    height,
    backgroundColor: 0x1099bb,
    resolution: window.devicePixelRatio || 1,
  });

  document.getElementById('app').appendChild(app.view);

  this.main = callback => {
    app.ticker.add(() => {
      const deltaTime = app.ticker.deltaMS / 1000;
      callback(deltaTime);
    });
  };

  this.addChild = (obj) => {
    app.stage.addChild(obj);
  };

  this.createContainer = () => {
    return new Container();
  };

  this.createSprite = name => {
    const texture = loadTexture(name);
    return new Sprite(texture);
  };
};
