import dbm

from flask import Flask, request, json, jsonify


app = Flask(__name__)


def get_count():
    with dbm.open('db/dbm', 'c') as db:
        return int(db.get('count', 0))


def update_count(count):
    with dbm.open('db/dbm', 'c') as db:
        db['count'] = str(count)


def get_posts():
    with dbm.open('db/dbm', 'c') as db:
        data = db.get('posts', '[]')
        return json.loads(data)


def insert_post(post):
    with dbm.open('db/dbm', 'c') as db:
        data = db.get('posts', '[]')
        posts = json.loads(data)
        posts.append(post)
        db['posts'] = json.dumps(posts)


@app.route('/')
def home():
    count = get_count()
    count += 1
    update_count(count)
    return jsonify(
        text='Hello, world',
        count=count
    )


@app.route('/abuse')
def abuse_count():
    count = get_count()
    count += 100
    update_count(count)
    return ''


@app.route('/posts', methods=['get'])
def post_list():
    posts = get_posts()
    return jsonify(
        posts=posts
    )


@app.route('/posts', methods=['post'])
def post_create():
    post = request.get_json()
    insert_post(post)
    return '', 201
