from models import Post


def test_post_creation():
    post = Post('Hahaha')
    assert post.created_at
