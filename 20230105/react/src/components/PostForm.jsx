import useCreatePost from '../hooks/useCreatePost';

export default function PostForm() {
  const { createPost } = useCreatePost();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const data = [...form.elements]
      .filter((element) => element.name)
      .reduce((acc, element) => ({
        ...acc,
        [element.name]: element.value,
      }), {});
    if (!data.title || !data.content) {
      return;
    }
    await createPost(data);
    window.location.reload();
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="input-title">제목</label>
        <input type="text" name="title" id="input-title" />
      </div>
      <div>
        <label htmlFor="input-content">본문</label>
        <input type="text" name="content" id="input-content" />
      </div>
      <button type="submit">
        등록
      </button>
    </form>
  );
}
