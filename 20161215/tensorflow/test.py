import tensorflow as tf
from tensorflow.contrib.learn.python.learn.datasets.mnist import read_data_sets


def main():
    mnist = read_data_sets('/tmp/tensorflow/mnist/input_data', one_hot=True)

    n = 28 * 28
    k = 10
    rate = 0.5

    w = tf.Variable(tf.zeros((n, k)))
    b = tf.Variable(tf.zeros((k)))

    x = tf.placeholder(tf.float32, (None, n))
    y = tf.placeholder(tf.float32, (None, k))

    u = tf.matmul(x, w) + b
    z = tf.nn.softmax(u)

    cost = tf.reduce_mean(-tf.reduce_sum(y * tf.log(z), 1))
    optimizer = tf.train.GradientDescentOptimizer(rate).minimize(cost)

    session = tf.Session()
    session.run(tf.initialize_all_variables())

    for i in range(1000):
        print(i)
        x_data, y_data = mnist.train.next_batch(100)
        session.run(optimizer, {x: x_data, y: y_data})

    #

    x_data, y_data = mnist.test.images, mnist.test.labels
    correct = tf.equal(tf.argmax(y, 1), tf.argmax(z, 1))
    accuracy = tf.reduce_mean(tf.cast(correct, tf.float32))
    print(session.run(accuracy, {x: x_data, y: y_data}))

    #

    def draw(image):
        print('--' * 28)
        for i in range(28):
            offset = i * 28
            print(''.join(['%2d' % (j * 10)
                           for j in image[offset:offset + 28]]))

    z_data = session.run(tf.argmax(z, 1), {x: x_data, y: y_data})
    corrects = session.run(correct, {x: x_data, y: y_data})

    count = 3
    for i in range(len(corrects)):
        if corrects[i]:
            continue
        draw(x_data[i])
        print('right: %d / predict: %d' % (list(y_data[i]).index(1), z_data[i]))
        count -= 1
        if count is 0:
            break


if __name__ == '__main__':
    main()
