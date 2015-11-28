import 'zone.js';
import 'reflect-metadata';
import {bootstrap, Component, View, Input} from 'angular2/angular2';

class Node {
  name: String;
  children: Array<Node>;
  constructor(name) {
    this.name = name;
    this.children = new Array<Node>();
  }
  isEmpty(): Boolean {
    return this.children.length == 0;
  }
  add(node) {
    this.children.push(node);
  }
}

@Component({
  selector: 'my-node'
})
@View({
  directives: [NodeComponent],
  template: `
    {{node.name}}
    <ul *ng-if="!node.isEmpty()">
      <li *ng-for="#child of node.children">
        <my-node [node]="child"></my-node>
      </li>
    </ul>
    <div *ng-if="!name">
      <button (click)="onClickAdd()">+</button>
    </div>
    <div *ng-if="name">
      <input [(ng-model)]="name" placeholder="Name">
      <button (click)="onSubmitAdd()">Add</button>
    </div>
    `,
  styles: [`
    input {
      padding: 0.1em;
      font-size: 0.8em;
      border: 2px solid #CCC;
    }
    `]
)
class NodeComponent {
  @Input() node: Node;
  name: String;
  onClickAdd() {
    this.name = 'new';
  }
  onSubmitAdd() {
    this.node.add(new Node(this.name));
    this.name = '';
  }
}

@Component({
  selector: 'my-app'
})
@View({
  directives: [NodeComponent],
  template: `
    <main>
      <h1>Tree</h1>
      <my-node [node]="root"></my-node>
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
    `]
})
class AppComponent {
  root: Node;
  name: string;
  constructor() {
    this.root = new Node();
  }
}

bootstrap(AppComponent);
