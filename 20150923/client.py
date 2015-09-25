import json
import othello
import requests
import sys
import time


class Game:
    def __init__(self):
        self.key = None
        self.stone = None
        self.other_stone = None
        self.board = othello.Board()

    def init(self, player):
        self.key = 'player%d' % player
        if player is 0:
            self.stone = othello.BLACK
            self.other_stone = othello.WHITE
        else:
            self.stone = othello.WHITE
            self.other_stone = othello.BLACK

    def init_board(self, data):
        self.board.from_str(data)
        print(str(self.board))
        print('----')

    def do_my_turn(self):
        positions = self.board.find(self.stone)
        if len(positions) is 0:
            print('FAIL!!!')
            return
        def score(x, y):
            return len(self.board.get_all_flips(self.stone, x, y))
        positions.sort(key=lambda i: score(i[0], i[1]))
        print(positions)
        x, y = positions[-1]
        r = requests.get(HOST + '/put', dict(key=self.key, x=x, y=y))
        print(r.content)
        self.board.put(self.stone, x, y)
        print(str(self.board))


HOST = 'http://localhost:5000'
# HOST = 'http://othello.ahastudio.com'

game = Game()


def main():
    r = requests.get(HOST + '/enter', {'key': game.key})
    print(r.content)
    r = requests.get(HOST + '/start', {'key': game.key})
    print(r.content)
    other = dict(x=None, y=None)
    r = requests.get(HOST + '/board')
    game.init_board(r.content)
    game.do_my_turn()
    while True:
        r = requests.get(HOST + '/get', {'key': game.key})
        history = json.loads(r.content)
        if len(history) is 0:
            continue
        x, y = history[-1]
        if x is other['x'] and y is other['y']:
            continue
        game.board.put(game.other_stone, x, y)
        other = dict(x=x, y=y)
        # time.sleep(1)
        game.do_my_turn()


if __name__ == '__main__':
    if len(sys.argv) is 2:
        player = int(sys.argv[1])
        if player in [0, 1]:
            game.init(player)
            main()
