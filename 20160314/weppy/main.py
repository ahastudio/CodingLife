from weppy import App, request


app = App(__name__)


@app.route('/')
def welcome():
    # request.params 작동이 좀 특이함. 다른 건 빡빡한데 얘는 왜 그냥 None 주지?
    return dict(name=request.params.name or 'world')


@app.route('/posts', methods='get')
def post_list():
    return '왜 methods가 옵션인 거죠? JAX-RS보다 나쁘잖아요?'


@app.route('/posts/<int:post_id>', methods='get')
def post_detail(post_id):
    # 1. post_id가 int 타입이 아니면 “Invalid action” 에러가 난다.
    # 2. int라고 했지만 post_id는 결국 문자열로 들어온다.
    return 'Post - %s' % post_id


if __name__ == '__main__':
    app.run()
