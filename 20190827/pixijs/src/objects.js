export const TILE_SIZE = 16;
export const BOARD_SIZE = 20;

const randInt = size => Math.floor(Math.random() * size);

export class Snake {
  constructor(scene) {
    this.dead = false;
    this.speed = 1;
    this.pieces = [];
    this.velocity = { x: 0, y: 1 };
    this.elapsedTime = 0;

    const container = scene.createContainer();
    scene.addChild(container);

    this.addPiece(scene, { x: 0, y: 0 });
  }

  update(scene, deltaTime) {
    if (this.dead) {
      this.elapsedTime = 0;
      return;
    }

    this.elapsedTime += deltaTime;

    const time = 0.1 + 0.5 * (10 - Math.min(10, this.speed)) / 10;
    if (this.elapsedTime > time) {
      this.elapsedTime -= time;
      this.move();
      this.checkLife();
    }

    this.updatePieces();
  }

  setPosition({ x, y }) {
    this.pieces[0].position = { x, y };
  }

  getPosition() {
    const { x, y } = this.pieces[0].position;
    return { x, y };
  }

  move() {
    const { x, y } = this.getPosition();
    this.pieces.reduce((a, e) => e.updatePosition(a), {
      x: x + this.velocity.x,
      y: y + this.velocity.y,
    });
  }

  checkLife() {
    const { x, y } = this.getPosition();
    if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
      this.dead = true;
      return;
    }
    const [_head, ...tail] = this.pieces;
    this.dead = tail.some(({ position: pos }) => x === pos.x && y === pos.y);
  }

  addPiece(scene, position = null) {
    const { x, y } = position || this.getPosition();
    const piece = new SnakePiece({ scene, x, y });

    this.pieces.push(piece);
  }

  updatePieces() {
    this.pieces.forEach(i => i.update());
  }

  reset() {
    this.dead = false;
    this.velocity = { x: 0, y: 1 };
    this.pieces.forEach(i => { i.position = { x: 0, y: 0 }; });
  }
}

class SnakePiece {
  constructor({ scene, x, y }) {
    this.position = { x, y };

    this.sprite = scene.createSprite('snake');
    this.sprite.width = TILE_SIZE;
    this.sprite.height = TILE_SIZE;

    scene.addChild(this.sprite);

    this.update();
  }

  update() {
    const { x, y } = this.position;
    this.sprite.x = x * TILE_SIZE;
    this.sprite.y = y * TILE_SIZE;
  }

  updatePosition({ x, y }) {
    const oldPosition = this.position;
    this.position = { x, y };
    return oldPosition;
  }
}

export class Apple {
  constructor({ scene }) {
    const x = randInt(BOARD_SIZE);
    const y = randInt(BOARD_SIZE);

    this.position = { x, y };

    this.sprite = scene.createSprite('apple');
    this.sprite.width = TILE_SIZE;
    this.sprite.height = TILE_SIZE;
    this.sprite.x = x * TILE_SIZE;
    this.sprite.y = y * TILE_SIZE;

    scene.addChild(this.sprite);
  }

  destroy() {
    this.sprite.destroy();
    this.sprite = null;
  }
}
