import tensorflow as tf
from tensorflow.contrib.learn.python.learn.datasets.mnist import read_data_sets


class Layer:
    def initial(self, shape):
        # return tf.zeros(shape)
        return tf.truncated_normal(shape, stddev=0.1)


class ConvLayer(Layer):
    def __init__(self, in_channels, out_channels):
        size = 5
        initial = self.initial([size, size, in_channels, out_channels])
        self.w = tf.Variable(initial)
        self.b = tf.Variable(tf.constant(0.1, shape=[out_channels]))

    def run(self, source):
        u = tf.nn.conv2d(source, self.w, strides=[1, 1, 1, 1],
                         padding='SAME') \
            + self.b
        z = tf.nn.relu(u)
        return z


class PoolingLayer(Layer):
    def run(self, source):
        return tf.nn.max_pool(source, ksize=[1, 2, 2, 1], strides=[1, 2, 2, 1],
                              padding='SAME')


class Perceptron(Layer):
    def __init__(self, in_size, out_size, fn):
        self.w = tf.Variable(self.initial([in_size, out_size]))
        self.b = tf.Variable(tf.constant(0.1, shape=[out_size]))
        self.fn = fn

    def run(self, source):
        u = tf.matmul(source, self.w) + self.b
        z = self.fn(u)
        return z


def create_perceptron(size, n, k, x, y, keep_prob):
    rate = 0.5

    output = Perceptron(n, k, tf.nn.softmax)
    z = output.run(x)

    cost = tf.reduce_mean(-tf.reduce_sum(y * tf.log(z), 1))
    optimizer = tf.train.GradientDescentOptimizer(rate).minimize(cost)

    return optimizer, z


def create_cnn(size, n, k, x, y, keep_prob):
    rate = 1e-4
    conv_size = 5
    layers_count = 2
    feature_map_counts = [1, 32, 64]
    fully_connected_size = 1024

    image = tf.reshape(x, [-1, size, size, 1])

    pool = PoolingLayer()
    feature = image

    for i in range(layers_count):
        conv = ConvLayer(feature_map_counts[i], feature_map_counts[i + 1])
        feature = pool.run(conv.run(feature))

    last_size = size // (2 ** layers_count)
    full_count = last_size * last_size * feature_map_counts[-1]

    flat = tf.reshape(feature, [-1, full_count])

    fc = Perceptron(full_count, fully_connected_size, tf.nn.relu)
    fully_connected = fc.run(flat)

    drop = tf.nn.dropout(fully_connected, keep_prob)

    output = Perceptron(fully_connected_size, k, tf.nn.softmax)
    z = output.run(drop)

    cost = tf.reduce_mean(-tf.reduce_sum(y * tf.log(z), 1))
    optimizer = tf.train.AdamOptimizer(rate).minimize(cost)

    return optimizer, z


def main():
    mnist = read_data_sets('/tmp/tensorflow/mnist/input_data', one_hot=True)

    size = 28
    n = size * size
    k = 10
    x = tf.placeholder(tf.float32, (None, n))
    y = tf.placeholder(tf.float32, (None, k))
    keep_prob = tf.placeholder(tf.float32)

    optimizer, z = create_cnn(size, n, k, x, y, keep_prob)
    # optimizer, z = create_perceptron(size, n, k, x, y, keep_prob)

    session = tf.Session()
    session.run(tf.global_variables_initializer())

    for i in range(1000):
        print(i)
        x_data, y_data = mnist.train.next_batch(100)
        session.run(optimizer, {x: x_data, y: y_data, keep_prob: 0.5})

    #

    x_data, y_data = mnist.test.images, mnist.test.labels
    correct = tf.equal(tf.argmax(y, 1), tf.argmax(z, 1))
    accuracy = tf.reduce_mean(tf.cast(correct, tf.float32))
    result = session.run(accuracy, {x: x_data, y: y_data, keep_prob: 1.0})
    print(result)

    #

    def draw(image):
        print('--' * 28)
        for i in range(28):
            offset = i * 28
            lines = image[offset:offset + 28]
            print(''.join(['%2d' % (j * 10) for j in lines]))

    z_data = session.run(tf.argmax(z, 1),
                         {x: x_data, y: y_data, keep_prob: 1.0})
    corrects = session.run(correct, {x: x_data, y: y_data, keep_prob: 1.0})

    count = 3
    for i in range(len(corrects)):
        if corrects[i]:
            continue
        draw(x_data[i])
        print('right: %d / predict: %d' %
              (list(y_data[i]).index(1), z_data[i]))
        count -= 1
        if count is 0:
            break


if __name__ == '__main__':
    main()
