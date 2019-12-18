from models import Post


class PostService:
    def __init__(self):
        self.posts = []

    def get_posts(self):
        return self.posts

    def add_post(self, body):
        post = Post(body)
        self.posts.append(post)
