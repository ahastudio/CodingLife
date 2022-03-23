const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('electronAPI', {
  click: () => ipcRenderer.send('handle-click'),
});
