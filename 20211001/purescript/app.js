import { greeting } from './output/Main';

function main() {
  const el = document.querySelector('#app');
  el.innerHTML = greeting('world');
}

main();
