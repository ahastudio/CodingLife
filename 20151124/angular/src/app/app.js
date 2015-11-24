var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") return Reflect.decorate(decorators, target, key, desc);
    switch (arguments.length) {
        case 2: return decorators.reduceRight(function(o, d) { return (d && d(o)) || o; }, target);
        case 3: return decorators.reduceRight(function(o, d) { return (d && d(target, key)), void 0; }, void 0);
        case 4: return decorators.reduceRight(function(o, d) { return (d && d(target, key, o)) || o; }, desc);
    }
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var angular2_1 = require('angular2/angular2');
var AppComponent = (function () {
    function AppComponent() {
        this.name = 'Angular 2';
        this.nodes = ['하하하', ("1 + 2 = " + (1 + 2)), 'Angualr 2'];
    }
    AppComponent.prototype.onClick = function (node) {
        this.name = node;
    };
    AppComponent = __decorate([
        angular2_1.Component({
            selector: 'my-app',
            template: "\n    <main>\n      <h1>Hello, {{name}}!</h1>\n      <input [(ng-model)]=\"name\" placeholder=\"Name\">\n      <ul *ng-if=\"nodes.length\">\n        <li *ng-for=\"#node of nodes\" (click)=\"onClick(node)\">\n          {{node}}\n        </li>\n      </ul>\n    </main>\n    ",
            styles: ["\n    main {\n      width: 50%;\n      margin: 20px auto;\n    }\n    h1 {\n      margin: 0;\n      font-size: 2.0em;\n    }\n    input {\n      padding: 0.5em;\n      font-size: 1.0em;\n      border: 2px solid #CCC;\n    }\n    "]
        }), 
        __metadata('design:paramtypes', [])
    ], AppComponent);
    return AppComponent;
})();
angular2_1.bootstrap(AppComponent);
//# sourceMappingURL=app.js.map