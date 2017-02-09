import tensorflow as tf


def initial(shape):
    return tf.truncated_normal(shape, stddev=0.1)


class Cell:
    def __init__(self, in_size, out_size, fn):
        self.w_in = tf.Variable(initial([in_size, out_size]))
        self.w = tf.Variable(initial([out_size, out_size]))
        self.w_out = tf.Variable(initial([out_size, out_size]))
        self.fn = fn

    def input(self, x, z):
        if z is None:
            bias = 0.0
        else:
            bias = tf.matmul(z, self.w)
        u = tf.matmul(x, self.w_in) + bias
        return self.fn(u)

    def output(self, z):
        v = tf.matmul(z, self.w_out)
        return self.fn(v)


def create_rnn(n, k, x, y):
    rate = 0.1

    cell = Cell(k, k, tf.nn.softmax)

    z = None
    for i in range(n):
        xt = tf.slice(x, [0, i, 0], [-1, 1, k])
        z = cell.input(tf.reshape(xt, [-1, k]), z)
    z = cell.output(z)

    cost = tf.reduce_mean(-tf.reduce_sum(y * tf.log(z), 1))
    optimizer = tf.train.AdamOptimizer(rate).minimize(cost)

    return optimizer, z, cell


def main():
    text = open('data.txt').read().replace('\n', ' ')

    chars = list(set(text))
    dic = {c: i for i, c in enumerate(chars)}
    n = 3
    k = len(chars)

    def vector(char):
        v = [0.0] * k
        v[dic[char]] = 1.0
        return v

    print('create x_data...')
    xs = [list(text[i:i+n]) for i in range(len(text) - n)]
    print('...')
    x_data = [[vector(c) for c in x] for x in xs]
    print('create y_data...')
    ys = [text[n + i] for i in range(len(text) - n)]
    print('...')
    y_data = [vector(c) for c in ys]
    print('--------')
    print(xs[:10])
    print(x_data[:1])
    print('--------')
    print(ys[:10])
    print(y_data[:1])
    print('--------')

    x = tf.placeholder(tf.float32, (None, n, k))
    y = tf.placeholder(tf.float32, (None, k))

    optimizer, z, cell = create_rnn(n, k, x, y)

    session = tf.Session()
    session.run(tf.global_variables_initializer())

    for i in range(100):
        print(i)
        for i in range(10):
            session.run(optimizer, {x: x_data, y: y_data})

    #

    print('n = %d, k = %d' % (n, k))
    print('========================================')

    def generate(text):
        for i in range(30):
            x_data = [[vector(c) for c in text[-n:]]]
            index = session.run(tf.argmax(z, 1), {x: x_data})[0]
            text += chars[index]
        return text

    print(generate('머신러닝을'))
    print('--------')
    print(generate('아샬을'))
    print('--------')
    print(generate('인공지능이'))
    print('--------')
    print(generate('인간은'))


if __name__ == '__main__':
    main()
