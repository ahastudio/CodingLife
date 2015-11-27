import 'zone.js';
import 'reflect-metadata';
import {bootstrap, Component, View} from 'angular2/angular2';

@Component({
  selector: 'my-app'
})
@View({
  template: `
    <main>
      <h1 (click)="onClickHeading()">Hello, {{name}}!</h1>
      <input [(ng-model)]="name" placeholder="Name">
    </main>
    `,
  styles: [`
    main {
      width: 50%;
      margin: 20px auto;
    }
    h1 {
      margin: 0;
      font-size: 2.0em;
    }
    input {
      padding: 0.5em;
      font-size: 1.0em;
      border: 2px solid #CCC;
    }
    `]
})
class AppComponent {
  name: string;
  constructor() {
    this.name = 'Angular 2';
  }
  onClickHeading() {
    this.name = 'Angular 2';
  }
}

bootstrap(AppComponent);
