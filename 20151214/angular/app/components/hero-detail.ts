import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, Router, RouteParams} from 'angular2/router';

@Component({
  selector: 'hero-detail',
  template: `
    <h1>Hero {{id}}</h1>
    <p>Hello, hero!</p>
    <p><button (click)="onClickBack()">Back</button></p>
  `,
  directives: [ROUTER_DIRECTIVES]
})
export class HeroDetailComponent {
  id: string;

  constructor(private _router: Router, params: RouteParams) {
    this.id = params.get('id');
    this.location = location;
  }

  onClickBack() {
    this._router.navigate(['HeroList']);
  }
}
