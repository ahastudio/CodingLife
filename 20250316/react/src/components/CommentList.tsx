import { Comment } from '../types';

export default function CommentList({ comments }: {
  comments: Comment[];
}) {
  return (
    <ul>
      {comments.map((comment) => (
        <li key={comment.id}>{comment.content}</li>
      ))}
    </ul>
  );
}
