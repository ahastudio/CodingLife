import 'zone.js';

import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

@Component({
    selector: 'my-app',
    template: '<h1>Hello, Angular 2</h1>'
})
class AppComponent {
}

@NgModule({
    declarations: [AppComponent],
    imports: [
        BrowserModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
class AppModule {
}

platformBrowserDynamic().bootstrapModule(AppModule)
    .catch(err => console.error(err));
