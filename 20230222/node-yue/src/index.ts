import gui from 'gui';

const win = gui.Window.create({ frame: false });
win.setAlwaysOnTop(true);
win.setContentSize({ width: 600, height: 300 });
win.onClose = () => gui.MessageLoop.quit();

win.center();
win.activate();

Reflect.set(global, 'win', win);

if (!process.versions.yode) {
  gui.MessageLoop.run();
  process.exit(0);
}
