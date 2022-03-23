console.log('renderer!');

const button = document.getElementById('button-click-me');
button.addEventListener('click', () => {
  window.electronAPI.click();
});
