import usePostFormStore from '../hooks/usePostFormStore';
import usePostStore from '../hooks/usePostStore';

export default function PostForm() {
  const postFormStore = usePostFormStore();
  const postStore = usePostStore();

  const handleSubmit = (event) => {
    // const form = event.target;
    // const post = {
    //   author: form.author.value,
    //   title: form.title.value,
    //   body: form.body.value,
    // };

    // const post = ['author', 'title', 'body'].reduce((acc, key) => ({
    //   ...acc,
    //   [key]: postFormStore[key],
    // }), {});

    const { author, title, body } = postFormStore;
    const post = { author, title, body };

    postStore.save(post);

    postFormStore.reset();

    event.preventDefault();
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>글 쓰기</h2>
      <div>
        <label htmlFor="input-author">
          작성자
        </label>
        <input
          id="input-author"
          type="text"
          name="author"
          value={postFormStore.author}
          onChange={(e) => postFormStore.changeAuthor(e.target.value)}
        />
      </div>
      <div>
        <label htmlFor="input-title">
          제목
        </label>
        <input
          id="input-title"
          type="text"
          name="title"
          value={postFormStore.title}
          onChange={(e) => postFormStore.changeTitle(e.target.value)}
        />
      </div>
      <div>
        <label htmlFor="input-body">
          내용
        </label>
        <textarea
          id="input-body"
          name="body"
          rows="8"
          value={postFormStore.body}
          onChange={(e) => postFormStore.changeBody(e.target.value)}
        />
      </div>
      <button type="submit">
        등록
      </button>
    </form>
  );
}
