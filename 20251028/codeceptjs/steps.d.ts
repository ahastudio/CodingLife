/// <reference types='codeceptjs' />
type steps_file = typeof import('./steps_file');

declare namespace CodeceptJS {
  interface SupportObject {
    I: I;
    // biome-ignore lint/suspicious/noExplicitAny: Allow any type
    current: any;
  }
  interface Methods extends Playwright {}
  interface I extends ReturnType<steps_file> {}
  namespace Translation {
    // biome-ignore lint/complexity/noBannedTypes: Allow empty interface
    type Actions = {};
  }
}
