/// <reference path="../node_modules/angular2/typings/browser.d.ts" />
System.register(['angular2/platform/browser', 'angular2/core', 'angular2/http'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var browser_1, core_1, http_1;
    var App;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            App = (function () {
                function App(http) {
                    var _this = this;
                    this.http = http;
                    this.name = 'World';
                    this.words = [];
                    this.http.get('data.json').subscribe(function (res) {
                        _this.words = res.json().words;
                    });
                }
                App = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        template: "\n    <h1>Hello, {{name}}!</h1>\n    <input [(ngModel)]=\"name\">\n    <ol *ngIf=\"words.length\">\n      <li *ngFor=\"#word of words\">{{word}}</li>\n    </ol>\n    ",
                        providers: [
                            http_1.HTTP_PROVIDERS
                        ]
                    }), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], App);
                return App;
            }());
            browser_1.bootstrap(App);
        }
    }
});
