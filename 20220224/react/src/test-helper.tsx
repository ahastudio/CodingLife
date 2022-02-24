/* eslint-disable import/prefer-default-export */

// eslint-disable-next-line import/no-extraneous-dependencies
import { render } from '@testing-library/react';

import { RecoilRoot } from 'recoil';
import RecoilNexus from 'recoil-nexus';

export function renderScreen(callback: Function) {
  render((
    <RecoilRoot>
      <RecoilNexus />
      {callback()}
    </RecoilRoot>
  ));
}
