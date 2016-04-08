import {Component} from 'angular2/core';

class Item {
  id: number;
  name: string;
}

@Component({
  selector: 'my-app',
  template: `
    <h1>My Application</h1>
    <ul *ngIf="items.length">
      <li *ngFor="#item of items">
        <a href="#" (click)="onClick(item)">{{item.name}}</a>
      </li>
    </ul>
    <div *ngIf="item">
      <h2>Detail</h2>
      <p>{{item.name}}</p>
    </div>
  `
})
export class AppComponent {
  items: Item[] = [];
  item: Item = null;

  constructor() {
    this.items = [
      {id: 1, name: 'A'},
      {id: 2, name: 'B'},
    ];
  }

  onClick(item: Item) {
    if (item == this.item) {
      this.item = null;
      return false;
    }
    this.item = item;
    return false;
  }
}
