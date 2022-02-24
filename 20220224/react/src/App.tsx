import { RecoilRoot } from 'recoil';
import RecoilNexus from 'recoil-nexus';

import Main from './components/Main';

export default function App() {
  return (
    <RecoilRoot>
      <RecoilNexus />
      <Main />
    </RecoilRoot>
  );
}
