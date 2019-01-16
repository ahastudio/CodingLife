const { app, BrowserWindow, ipcMain } = require('electron');

app.on('ready', () => {
  const win = new BrowserWindow({ width: 800, height: 400 });
  win.loadFile('index.html');
  console.log('main!');
});

ipcMain.on('handle-click', (event, arg) => {
  console.log('click!!!');
});
