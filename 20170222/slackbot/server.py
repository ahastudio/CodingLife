import json
from flask import Flask, request


app = Flask(__name__)

items = []


@app.route('/requests', methods=['GET'])
def get_requests():
    return json.dumps(items)


@app.route('/requests', methods=['POST'])
def create_request():
    item = {
        'id': str(len(items) + 1),
        'message': request.form['message']
    }
    items.append(item)
    return json.dumps(item)


@app.route('/requests/<request_id>', methods=['GET'])
def check_error_request(request_id):
    for item in items:
        if item['id'] == request_id:
            item['correct'] = False
    return '감사합니다'


if __name__ == '__main__':
    app.run()
