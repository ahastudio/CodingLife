import {
  beforeEachProviders,
  describe,
  expect,
  it,
  inject
} from '@angular/core/testing';
import { ApplicationAppComponent } from '../app/application.component';

beforeEachProviders(() => [ApplicationAppComponent]);

describe('App: Application', () => {
  it('should create the app',
      inject([ApplicationAppComponent], (app: ApplicationAppComponent) => {
    expect(app).toBeTruthy();
  }));

  it('should have as title \'application works!\'',
      inject([ApplicationAppComponent], (app: ApplicationAppComponent) => {
    expect(app.title).toEqual('application works!');
  }));
});
