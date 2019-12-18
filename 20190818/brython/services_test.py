from services import PostService


def test_add_post():
    POST_BODY = 'title'

    post_service = PostService()
    post_service.add_post(POST_BODY)

    post = post_service.get_posts()[-1]

    assert post.body == POST_BODY
