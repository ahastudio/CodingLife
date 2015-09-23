from flask import Flask, request, render_template

from game import Board, BLANK, BLACK, WHITE

import json


board = Board()
players = []
ready = []
playing = False
history = []

app = Flask(__name__)

@app.route('/')
def index():
    print(str(board))
    return render_template('index.html')

@app.route('/enter')
def enter():
    key = request.args.get('key', '')
    if len(players) < 2 and len(key) > 0 and key not in players:
        players.append(key)
    return '%d' % len(players)

@app.route('/leave')
def leave():
    key = request.args.get('key', '')
    if key in players:
        players.remove(key)
        ready.remove(key)
    return '%d' % len(players)

@app.route('/start')
def start():
    key = request.args.get('key', '')
    if key not in players:
        return ''
    if key not in ready:
        ready.append(key)
    playing = len(ready) is 2
    history = []
    return json.dumps(dict(start=playing))

@app.route('/put')
def put():
    key = request.args.get('key', '')
    if key not in players:
        return ''
    x = int(request.args.get('x', -1))
    y = int(request.args.get('y', -1))
    if board.get(x, y) is BLANK:
        index = players.index(key)
        stone = [BLACK, WHITE][index]
        board.put(stone, x, y)
        history.append(dict(player=index, x=x, y=y))
    return ''

@app.route('/get')
def get():
    key = request.args.get('key', '')
    if key not in players:
        return ''
    return json.dumps([[i['x'], i['y']] for i in history
                       if i['player'] is not players.index(key)])

if __name__ == '__main__':
    app.run(debug=True)
