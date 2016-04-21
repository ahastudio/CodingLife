import {Component, OnInit} from 'angular2/core';
import {RouteParams} from 'angular2/router';

@Component({
  template: `
    <p>Board - {{boardName}}</p>
    `
})
export class BoardComponent implements OnInit {
  boardName: string;

  constructor(private routeParams: RouteParams) {
  }

  ngOnInit() {
    this.boardName = this.routeParams.get('name');
  }
}
