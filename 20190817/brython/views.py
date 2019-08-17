def render_posts(posts):
    return ''.join(render_post(post) for post in posts)


def render_post(post):
    return f'<div class="post">{post.body} | {post.created_at}</div>'


def render_form():
    return '''
        <form id="form-add-post">
            <input type="text" id="input-body">
            <button type="submit">
                추가
            </button>
        </form>
    '''
