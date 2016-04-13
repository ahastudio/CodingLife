https://github.com/ahastudio/til/blob/master/angular/quickstart.md

Http Client
https://angular.io/docs/ts/latest/guide/server-communication.html

```html
<script src="node_modules/angular2/bundles/http.dev.js"></script>
```

https://github.com/angular/angular/blob/master/modules/angular2/http.ts

```typescript
export class App {
  people: Object[];
  constructor(http:Http) {
    http.get('people.json').subscribe(res => {
      this.people = res.json();
    });
  }
```
