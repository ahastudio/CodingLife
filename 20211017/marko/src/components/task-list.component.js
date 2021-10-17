export default class {
  handleTaskEvent(eventName, ...args) {
    this.emit(eventName, ...args);
  }
}
