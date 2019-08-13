import hug


@hug.get('/')
def home():
    return 'Hello, world!'


@hug.get('/hello')
def hello(name):
    return {'message': f'Hello, {name}'}
