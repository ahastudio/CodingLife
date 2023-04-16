import ClassicFileUploadForm from './components/ClassicFileUploadForm';
import AjaxFileUploadForm from './components/AjaxFileUploadForm';

export default function App() {
  return (
    <div>
      <h1>File Upload Demo</h1>
      <hr />
      <h2>전통적인 Form 사용</h2>
      <ClassicFileUploadForm />
      <hr />
      <h2>Fetch API 사용</h2>
      <AjaxFileUploadForm />
      <hr />
    </div>
  );
}
