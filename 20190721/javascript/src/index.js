const data = [
  {
    title: 'Red',
    color: '#F00',
    values: [50, 20, 40],
    ranks: [1, 3, 3],
  },
  {
    title: 'Green',
    color: '#0F0',
    values: [20, 90, 50],
    ranks: [2, 1, 2],
  },
  {
    title: 'Blue',
    color: '#00F',
    values: [10, 30, 60],
    ranks: [3, 2, 1],
  },
];

const rankToTop = rank => (rank - 1) * (30 + 5);

const createElement = ({ title, color, values, ranks }) => {
  const el = document.createElement('div');
  el.innerHTML = `
    <div class="item"
      style="top:${rankToTop(ranks[0])};width:${values[0]}%;background:${color}"
    >
      <span>${title}</span>
    </div>
  `.trim();
  return el.firstChild;
};

const init = (items) => {
  const el = document.getElementById('app');
  el.innerHTML = '';
  const elements = items.map(createElement);
  elements.forEach(i => el.appendChild(i));
  return (index) => {
    elements.forEach((el, i) => {
      const { values, ranks } = items[i];
      el.style.top = `${rankToTop(ranks[index % ranks.length])}px`;
      el.style.width = `${values[index % values.length]}%`;
    });
  };
};

const render = init(data);

let index = 0;
render(index);

setInterval(() => {
  index += 1;
  render(index);
}, 3000);
