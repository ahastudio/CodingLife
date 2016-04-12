/// <reference path="../node_modules/angular2/typings/browser.d.ts" />

import {bootstrap} from 'angular2/platform/browser';
import {Component} from 'angular2/core';

@Component({
  selector: 'my-app',
  template: `
    <h1>Hello, {{name}}!</h1>
    <input [(ngModel)]="name">
    <ol *ngIf="items.length">
      <li *ngFor="#item of items">{{item}}</li>
    </ol>
    `
})
class App {
  name: string = 'world';

  get items(): string[] {
    return this.name.split('');
  }
}

bootstrap(App);
