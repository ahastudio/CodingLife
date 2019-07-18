import pytest
import json

from app import app


@pytest.fixture
def client():
    return app.test_client()


def do_get(client, path):
    response = client.get(path)
    return response.status_code, str(response.data), response.get_json()


def do_post(client, path, data):
    response = client.post(path, data=json.dumps(data),
                           content_type='application/json')
    return response.status_code


def test_home(client):
    status_code, body, data = do_get(client, '/')
    oldCount = data['count']

    assert status_code == 200
    assert '"text":"Hello, world"' in body

    for i in range(5):
        status_code, body, data = do_get(client, '/')
        newCount = data['count']

        assert status_code == 200
        assert newCount == oldCount + i + 1


def test_abuse(client):
    status_code, body, data = do_get(client, '/')
    oldCount = data['count']

    assert status_code == 200

    status_code, _, _ = do_get(client, '/abuse')

    assert status_code == 200

    status_code, body, data = do_get(client, '/')
    newCount = data['count']

    assert status_code == 200
    assert newCount == oldCount + 100 + 1


def test_posts(client):
    status_code = do_post(client, '/posts', dict(
        title='Title',
        body='Text...',
    ))

    assert status_code == 201

    status_code, _, data = do_get(client, '/posts')
    posts = data['posts']

    assert status_code == 200
    assert type(posts) == list
    assert posts[0]['title'] == 'Title'
