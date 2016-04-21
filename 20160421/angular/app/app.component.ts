import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {HomeComponent} from './home.component';
import {BoardComponent} from './board.component';

@Component({
  selector: 'my-app',
  template: `
    <nav>
      <a [routerLink]="['Home']">Home</a>
      <a [routerLink]="['Board', {name: 'free'}]">Free Board</a>
    </nav>
    <router-outlet></router-outlet>
    `,
  directives: [ROUTER_DIRECTIVES]
})
@RouteConfig([
  {
    path: '/',
    name: 'Home',
    component: HomeComponent,
    useAsDefault: true
  },
  {
    path: '/board/:name',
    name: 'Board',
    component: BoardComponent
  }
])
export class AppComponent {
}
