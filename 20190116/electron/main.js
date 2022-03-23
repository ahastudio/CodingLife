const path = require('path')
const { app, BrowserWindow, ipcMain } = require('electron');

console.log('main!');

app.on('ready', () => {
  const win = new BrowserWindow({
    width: 800,
    height: 400,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
    },
  });

  win.loadFile('index.html');
});

ipcMain.on('handle-click', (event, arg) => {
  console.log('click!!!');
});
