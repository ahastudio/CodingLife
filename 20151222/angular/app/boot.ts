import 'reflect-metadata'
import {bootstrap} from 'angular2/platform/browser'
import {Component} from 'angular2/core'

@Component({
  selector: 'my-app',
  template: `
    <h1>Menu</h1>
    <ul *ngIf="data && data.groups">
      <li *ngFor="#group of data.groups">
        <h2>{{group.title}}</h2>
        <ul>
          <li *ngFor="#item of group.items">
            <input type="text" [(ngModel)]="item.name">
            {{item.price | number}}원
          </li>
        </ul>
      </li>
    </ul>
    <p>
      <button *ngIf="!data" (click)="onLoad()">Load from testData</button>
      <input type="text" *ngIf="data" [(ngModel)]="data.groups[0].title">
      <button (click)="onShow()">Show JSON</button>
    </p>
  `
})
class AppComponent {
  pubic data = {};

  onLoad() {
    this.data = window.testData;
  }

  onShow() {
    alert(JSON.stringify(this.data));
  }
}

bootstrap(AppComponent);

window.testData = {
  groups: [
    {
      title: '면류',
      items: [
        {
          name: '짜장면',
          price: 4500
        },
        {
          name: '짬뽕',
          price: 5500
        }
      ]
    },
    {
      title: '밥류',
      items: [
        {
          name: '볶음밥',
          price: 5500
        }
      ]
    }
  ]
};
