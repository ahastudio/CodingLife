import 'reflect-metadata'
import {bootstrap} from 'angular2/platform/browser'
import {Component} from 'angular2/core'

@Component({
  selector: 'my-app',
  template: `
    <form>
      <h1>Hello, {{name}}!</h1>
      <input [(ngModel)]="name" placeholder="Name">
      <button (click)="onSubmit()">Go!</button>
      <p>{{json}}</p>
    </form>
  `,
  styles: [`
    form {
      width: 50%;
      margin: 20px auto;
    }
    h1 {
      margin: 0;
      padding-bottom: 0.4em;
      font-size: 2.0em;
    }
    input, button {
      padding: 0.5em;
      font-size: 1.0em;
    }
  `]
})
class AppComponent {
  public name: string = 'Angular 2';

  public count: number = 0;
  public json: string = '';

  onSubmit() {
    this.json = JSON.stringify({
      count: this.count += 1,
      name: this.name,
      datetime: new Date()
    });
    this.name = 'Angular 2';
  }
}

bootstrap(AppComponent);
