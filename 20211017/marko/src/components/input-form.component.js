export default class {
  submit(e) {
    e.preventDefault();
    const el = this.getEl('body');
    this.emit('add-task', { body: el.value.trim() });
    el.value = '';
  }
}
