import { QMainWindow, QLabel } from '@nodegui/nodegui';

const win = new QMainWindow();

const label = new QLabel(win);
label.setText('Hello, world!');

win.show();

Reflect.set(global, 'win', win); // To prevent win from being garbage collected.
