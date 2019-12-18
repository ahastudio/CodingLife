from browser import window, document
from javascript import this

from services import PostService
from models import Post

Vue = window.Vue

post_service = PostService()


def initial_data(*_):
    return {
        'posts': [],
        'body': ''
    }


def mounted(*_):
    self = this()
    self['$refs'].body.focus()


def add_post(event, *_):
    event.preventDefault()
    self = this()
    post_service.add_post(self.body)
    self.body = ''
    self.posts = [x.dict() for x in post_service.get_posts()]


Vue.component('app', {
    'template': '''
        <div>
            <div v-for="post in posts" class="post">
                {{ post.body }} | {{ post.created_at }}
            </div>
            <form @submit="addPost">
                <input type="text" ref="body" v-model="body">
                <button type="button" @click="addPost">
                    추가
                </button>
            </form>
        </div>
    ''',
    'data': initial_data,
    'mounted': mounted,
    'methods': {
        'addPost': add_post
    },
})


if __name__ == '__main__':
    document['app'].innerHTML = '<app></app>'
    Vue.new({ 'el': '#app' })
