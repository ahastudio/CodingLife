from browser import document

from services import PostService
from views import render_posts, render_form


class App:
    def __init__(self):
        self.post_service = PostService()

    def run(self):
        self.update()

    def update(self):
        html = self.render()
        document['app'].innerHTML = html
        document['form-add-post'].bind('submit', self.add_post)
        document['input-body'].focus()

    def render(self):
        posts = self.post_service.get_posts()
        return ''.join([
            render_posts(posts),
            render_form(),
        ])

    def add_post(self, event):
        event.preventDefault()
        body = document['input-body'].value
        self.post_service.add_posts(body)
        self.update()


if __name__ == '__main__':
    app = App()
    app.run()
