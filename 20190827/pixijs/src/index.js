import { Scene } from './scene';
import { Snake, Apple } from './objects';

(() => {
  const scene = new Scene();

  const randInt = size => Math.floor(Math.random() * size);

  const createApple = () => {
    const x = randInt(10);
    const y = randInt(10);
    return new Apple({ scene, x, y });
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
