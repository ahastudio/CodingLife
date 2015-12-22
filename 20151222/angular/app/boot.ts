import 'reflect-metadata'
import {bootstrap} from 'angular2/platform/browser'
import {Component} from 'angular2/core'

@Component({
  selector: 'group-detail',
  template: `
    <h2>{{group.title}}</h2>
    <ul>
      <li *ngFor="#item of group.items">
        {{item.name}}
        {{item.price | number}}원
      </li>
    <ul>
  `,
  inputs: ['group']
})
class GroupComponent {
  public group;
}

@Component({
  selector: 'my-app',
  template: `
    <h1>Menu</h1>
    <ul *ngIf="data && data.groups">
      <li *ngFor="#group of data.groups">
        <group-detail [group]="group"></group-detail>
      </li>
    </ul>
    <p>
      <button (click)="onLoad()">Load from testData</button>
      <button (click)="onShow()">JSON to Console</button>
    </p>
    <p>1. 그냥 Load 버튼을 눌러보세요.</p>
    <p>2. 콘솔을 열어 다음을 입력하고 Load 버튼을 눌러보세요.</p>
    <pre>window.testData.groups[0].title = '그룹 #1'</pre>
  `,
  directives: [GroupComponent]
})
class AppComponent {
  pubic data = {};

  onLoad() {
    this.data = window.testData;
  }

  onShow() {
    console.log(JSON.stringify(this.data));
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
