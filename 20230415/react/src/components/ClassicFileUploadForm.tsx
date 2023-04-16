export default function ClassicFileUploadForm() {
  return (
    <form
      action="http://localhost:8080/images"
      method="POST"
      encType="multipart/form-data"
    >
      <p>
        <label htmlFor="input-file">File:</label>
        <input id="input-file" type="file" name="image" />
      </p>
      <p>
        <button type="submit">
          Submit
        </button>
      </p>
    </form>
  );
}
