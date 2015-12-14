import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

class Hero {
  public id: String;
  public name: String;
}

@Component({
  selector: 'hero-list',
  template: `
    <div>
      <h1>Hero list</h1>
      <ul>
        <li *ngFor="#hero of heros; #i = index">
          <a [routerLink]="['HeroDetail', {id: hero.id}]">{{hero.name}}</a>
        </li>
      </ul>
    </div>
  `,
  directives: [ROUTER_DIRECTIVES]
})
export class HeroListComponent {
  heros: Hero[] = [];

  constructor() {
    for (var i = 0; i < 10; i++) {
      var hero = new Hero();
      hero.id = '' + (i + 1);
      hero.name = 'Hero #' + (i + 1);
      this.heros.push(hero);
    }
  }
}
