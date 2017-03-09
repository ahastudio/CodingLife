import json
import urllib.parse
from flask import Flask, request, redirect


app = Flask(__name__)

items = []


@app.route('/requests', methods=['GET'])
def get_requests():
    return json.dumps(items)


@app.route('/requests', methods=['POST'])
def create_request():
    item = {
        'id': str(len(items) + 1),
        'message': request.form['message'],
        'keyword': request.form['keyword'],
    }
    if 'url' in request.form:
        item['url'] = request.form['url']
    items.append(item)
    return json.dumps(item)


@app.route('/requests/<request_id>', methods=['GET'])
def check_error_request(request_id):
    keyword = None
    for item in items:
        if item['id'] == request_id:
            item['hit'] = item.get('hit', 0) + 1
            if 'url' in item:
                url = item['url']
            else:
                keyword = item['keyword']
                url = 'https://www.google.com/search?q=' + \
                      urllib.parse.quote(keyword)
            return redirect(url)
    return '???'


if __name__ == '__main__':
    app.run()
