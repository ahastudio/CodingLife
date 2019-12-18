{ app, BrowserWindow } = require('electron')

createWindow = ->
  mainWindow = new BrowserWindow
    width: 800
    height: 600
  mainWindow.loadFile('./index.html')
  mainWindow.webContents.openDevTools()

app.whenReady().then ->
  createWindow()
  app.on 'activate', ->
    if BrowserWindow.getAllWindows().length is 0
      createWindow()

app.on 'window-all-closed', ->
  if process.platform isnt 'darwin'
    app.quit()
