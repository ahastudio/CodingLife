import React from 'react';
import { WebView } from 'react-native-webview';

const script = `
  document.querySelector('nav').remove();
  document.querySelector('footer').remove();
  document.querySelector('article > header').remove();
  document.querySelector('article > div:nth-of-type(3)').remove();
  document.querySelector('article > div:nth-of-type(2)').remove();
`;

export default function App() {
  return (
    <WebView
      source={{ uri: 'https://www.instagram.com/p/B9PB--QjTDg/' }}
      injectedJavaScript={script}
    />
  );
}
