const { ipcRenderer } = require('electron');

console.log('renderer!');

window.handleClick = () => {
  ipcRenderer.send('handle-click');
};
