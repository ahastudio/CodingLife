const robot = require('robotjs');

const { log: print } = console;

robot.setMouseDelay(2);

function main() {
  const { width, height } = robot.getScreenSize();
  const { x, y } = robot.getMousePos();

  print(`Window size: ${width}x${height}`);
  print(`Mouse position: ${x}, ${y}`);
}

main();
