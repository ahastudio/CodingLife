import axios from 'axios';

import { useState } from 'react';

export default function AjaxFileUploadForm() {
  const [file, setFile] = useState<File>();
  const [processing, setProcessing] = useState(false);
  const [complete, setComplete] = useState(false);
  const [message, setMessage] = useState('');
  const [error, setError] = useState<unknown>();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    setProcessing(true);
    setComplete(true);
    setMessage('');
    setError('');

    try {
      const url = 'http://localhost:8080/images';
      const { data } = await axios.postForm(url, { image: file });
      setComplete(true);
      setMessage(data);
    } catch (e) {
      setError({
        type: axios.isAxiosError(e) ? 'AxiosError' : 'unknown',
        error: e,
      });
    }

    setProcessing(false);
  };

  const handleChangeFile = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { files } = event.target;
    setFile(files ? files[0] : undefined);
  };

  return (
    <form onSubmit={handleSubmit}>
      <p>
        <label htmlFor="input-file">File:</label>
        <input
          id="input-file"
          type="file"
          onChange={handleChangeFile}
        />
      </p>
      <p>
        <button type="submit" disabled={processing}>
          Submit
        </button>
      </p>
      {!!complete && (
        <p>
          Complete: “
          {message}
          ”
        </p>
      )}
      {!!error && (
        <pre>{JSON.stringify(error, null, '  ')}</pre>
      )}
    </form>
  );
}
