from flask import Flask, request, render_template

from othello import BLANK, BLACK, WHITE, Board, Room

import json


room = Room()

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/board')
def do_get_board():
    return str(room.board)

@app.route('/enter')
def enter():
    key = request.args.get('key', '')
    if len(room.players) < 2 and len(key) > 0 and key not in room.players:
        room.players.append(key)
    return '%d' % len(room.players)

@app.route('/leave')
def leave():
    key = request.args.get('key', '')
    if key in room.players:
        room.players.remove(key)
        room.ready.remove(key)
    return '%d' % len(room.players)

@app.route('/start')
def start():
    key = request.args.get('key', '')
    if key not in room.players:
        return ''
    if key not in room.ready:
        room.ready.append(key)
    room.playing = len(room.ready) is 2
    room.history = []
    return json.dumps(dict(start=room.playing))

@app.route('/put')
def put():
    key = request.args.get('key', '')
    if key not in room.players:
        return ''
    index = room.players.index(key)
    if index is not room.turn:
        return ''
    x = int(request.args.get('x', -1))
    y = int(request.args.get('y', -1))
    if room.board.get(x, y) is BLANK:
        stone = [BLACK, WHITE][index]
        room.board.put(stone, x, y)
        room.history.append(dict(player=index, x=x, y=y))
        room.turn = (room.turn + 1) % 2
    return ''

@app.route('/get')
def get():
    key = request.args.get('key', '')
    if key not in room.players:
        return ''
    return json.dumps([[i['x'], i['y']] for i in room.history
                       if i['player'] is not room.players.index(key)])

if __name__ == '__main__':
    app.run(debug=True)
