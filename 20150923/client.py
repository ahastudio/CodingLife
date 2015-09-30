import json
import othello
import random
import requests
import time
import sys


HOST = 'http://localhost:5000'


class Game:
    def __init__(self):
        self.player = None
        self.stone = None
        self.other_stone = None
        self.turn = None
        self.board = othello.Board()

    def init(self, player):
        self.player = player
        stones = [othello.BLACK, othello.WHITE]
        self.stone = stones[player]
        self.other_stone = stones[1 - player]

    def load_data(self, data):
        self.turn = int(data['turn'])
        self.board.from_str(str(data['board']))
        print(str(self.board))
        print('----')

    def do_my_turn(self):
        positions = self.board.find(self.stone)
        if len(positions) is 0:
            print('FAIL!!!')
            return
        def bonus(x, y):
            if x in [0, othello.WIDTH - 1] and y in [0, othello.HEIGHT - 1]:
                return 10
            if x in [0, othello.WIDTH - 1] and y in [1, othello.HEIGHT - 2]:
                return -1
            if x in [1, othello.WIDTH - 2] and y in [0, othello.HEIGHT - 1]:
                return -1
            if x in [0, othello.WIDTH - 1] or y in [0, othello.HEIGHT - 1]:
                return 3
            return 0
        def other_score(x, y):
            board = othello.Board()
            board.from_str(str(self.board))
            board.put(self.stone, x, y)
            positions = self.board.find(self.other_stone)
            scores = [score(self.other_stone, x, y) for x, y in positions]
            if len(scores):
                return max(scores)
            return 0
        def score(stone, x, y):
            return len(self.board.get_all_flips(self.stone, x, y)) + bonus(x, y)
        positions.sort(key=lambda i: score(self.stone, i[0], i[1]) -
                                     other_score(i[0], i[1]))
        x, y = positions[-1]
        r = requests.get(HOST + '/put', dict(key=self.key, x=x, y=y))
        self.board.put(self.stone, x, y)
        print(str(self.board))


game = Game()


def main():
    while True:
        r = requests.get(HOST + '/start', {'key': game.key})
        if r.content and json.loads(r.content)['start']:
            break
        time.sleep(1)
    other = dict(x=None, y=None)
    r = requests.get(HOST + '/board')
    game.load_data(json.loads(r.content))
    if game.turn is game.player:
        game.do_my_turn()
    while True:
        time.sleep(1)
        r = requests.get(HOST + '/get', {'key': game.key})
        history = json.loads(r.content)
        if len(history) is 0:
            continue
        x, y = history[-1]
        if x is other['x'] and y is other['y']:
            continue
        game.board.put(game.other_stone, x, y)
        other = dict(x=x, y=y)
        game.do_my_turn()


if __name__ == '__main__':
    if len(sys.argv) >= 2:
        HOST = sys.argv[1]
    game.key = str(random.randint(0, 10000))
    print('KEY: %s' % game.key)
    r = requests.get(HOST + '/enter', {'key': game.key})
    player = int(r.content) - 1
    print('Player: %d' % player)
    if player in [0, 1]:
        game.init(player)
        main()
