/// <reference path="../node_modules/angular2/typings/browser.d.ts" />

import {bootstrap} from 'angular2/platform/browser';
import {Component} from 'angular2/core';
import {Http, HTTP_PROVIDERS} from 'angular2/http';

@Component({
  selector: 'my-app',
  template: `
    <h1>Hello, {{name}}!</h1>
    <input [(ngModel)]="name">
    <ol *ngIf="words.length">
      <li *ngFor="#word of words">{{word}}</li>
    </ol>
    `,
  providers: [
    HTTP_PROVIDERS
  ]
})
class App {
  name: string = 'World';
  words: string[] = [];

  constructor(private http: Http) {
    this.http.get('data.json').subscribe(res => {
      this.words = res.json().words;
    });
  }
}

bootstrap(App);
