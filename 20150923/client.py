import json
import othello
import requests
import time


HOST = 'http://localhost:5000'
KEY = 'test'

board = othello.Board()
stone = othello.BLACK
other_stone = othello.WHITE

def init_board(data):
    rows = data.split('\n')
    for y in range(othello.HEIGHT):
        for x in range(othello.WIDTH):
            cell = rows[y][x]
            if cell is 'X':
                board.set(othello.BLACK, x, y)
            if cell is 'O':
                board.set(othello.WHITE, x, y)

def do_my_turn():
    positions = board.find(stone)
    if len(positions) is 0:
        print('FAIL!!!')
        return
    x, y = positions[0]
    r = requests.get(HOST + '/put', dict(key=KEY, x=x, y=y))
    print(r.content)
    board.put(stone, x, y)
    print(str(board))

def main():
    r = requests.get(HOST + '/enter', {'key': KEY})
    print(r.content)
    r = requests.get(HOST + '/start', {'key': KEY})
    print(r.content)
    other = dict(x=None, y=None)
    r = requests.get(HOST + '/board')
    init_board(r.content)
    do_my_turn()
    while True:
        r = requests.get(HOST + '/get', {'key': KEY})
        history = json.loads(r.content)
        if len(history) is 0:
            continue
        x, y = history[-1]
        if x is other['x'] and y is other['y']:
            continue
        board.put(other_stone, x, y)
        other = dict(x=x, y=y)
        do_my_turn()
        time.sleep(5)


if __name__ == '__main__':
    main()
