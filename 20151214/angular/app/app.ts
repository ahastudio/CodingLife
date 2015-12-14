import 'zone.js';
import 'reflect-metadata';
import {bootstrap} from 'angular2/bootstrap';
import {Component, provide} from 'angular2/core';
import {ROUTER_DIRECTIVES, ROUTER_PROVIDERS, APP_BASE_HREF, RouteConfig}
    from 'angular2/router';
import {HeroListComponent} from './components/hero-list';
import {HeroDetailComponent} from './components/hero-detail';

@Component({
  selector: 'my-app',
  template: `
    <div>
      <nav>
        <a [routerLink]="['HeroList']">Home</a>
        |
        <a [routerLink]="['HeroDetail', {id: '1'}]">Hero #1</a>
      </nav>
      <router-outlet></router-outlet>
    </div>
  `,
  directives: [ROUTER_DIRECTIVES]
})
@RouteConfig([
  {path: '/', component: HeroListComponent, name: 'HeroList'},
  {path: '/hero/:id', component: HeroDetailComponent, name: 'HeroDetail'}
])
class AppComponent {}

bootstrap(AppComponent, [
  ROUTER_PROVIDERS,
  provide(APP_BASE_HREF, {useValue: '/'})
]);
