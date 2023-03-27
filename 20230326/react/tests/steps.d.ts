/// <reference types='codeceptjs' />

type steps_file = typeof import('./steps_file');
type backdoor = typeof import('./backdoor');

declare namespace CodeceptJS {
  interface SupportObject {
    I: I;
    backdoor: backdoor;
  }

  interface Methods extends Playwright, REST, JSONResponse {}

  interface I extends ReturnType<steps_file> {}

  namespace Translation {
    interface Actions {}
  }
}
