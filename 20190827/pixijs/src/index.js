import { Scene } from './scene';
import { TILE_SIZE, BOARD_SIZE, Snake, Apple } from './objects';

(() => {
  const scene = new Scene({
    width: TILE_SIZE * BOARD_SIZE,
    height: TILE_SIZE * BOARD_SIZE,
  });

  const createApple = () => {
    return new Apple({ scene });
  };

  const snake = new Snake(scene);

  let apple = createApple();

  scene.main(deltaTime => {
    snake.update(scene, deltaTime);

    if (snake.dead) {
      // GAME OVER
      snake.reset();
    }

    if (!apple) {
      return;
    }

    const { x, y } = snake.getPosition();
    if (x === apple.position.x && y === apple.position.y) {
      snake.addPiece(scene);
      snake.speed += 1;
      apple.destroy();
      apple = createApple();
    }
  });

  window.addEventListener('keydown', event => {
    const fn = {
      'ArrowUp': () => { snake.velocity = { x: 0, y: -1 }; },
      'ArrowDown': () => { snake.velocity = { x: 0, y: +1 }; },
      'ArrowLeft': () => { snake.velocity = { x: -1, y: 0 }; },
      'ArrowRight': () => { snake.velocity = { x: +1, y: 0 }; },
    }[event.key];
    fn && fn();
  });
})();
