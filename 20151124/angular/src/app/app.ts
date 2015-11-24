import {bootstrap, Component} from 'angular2/angular2';

@Component({
  selector: 'my-app',
  template: `
    <main>
      <h1>Hello, {{name}}!</h1>
      <input [(ng-model)]="name" placeholder="Name">
      <ul *ng-if="nodes.length">
        <li *ng-for="#node of nodes" (click)="onClick(node)">
          {{node}}
        </li>
      </ul>
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
  public name = 'Angular 2';
  public nodes = ['하하하', `1 + 2 = ${1 + 2}`, 'Angualr 2'];

  onClick(node) {
    this.name = node;
  }
}

bootstrap(AppComponent);
