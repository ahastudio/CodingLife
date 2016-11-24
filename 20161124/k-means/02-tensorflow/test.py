import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt


def create_data():
    return np.concatenate([np.random.standard_normal((500, 2)) * 0.9 + [6, 6],
                           np.random.standard_normal((300, 2)) * 0.6 + [6, 1],
                           np.random.standard_normal((300, 2)) * 0.6 + [4, 3]])

def main():
    k = 3

    data = create_data()
    vectors = tf.constant(data)

    indices = np.arange(len(data))
    np.random.shuffle(indices)
    centroids = tf.Variable(data[indices[:k]])

    def distance(a, b):
        return tf.reduce_sum(tf.square(a - b), 2)

    distances = distance(tf.expand_dims(vectors, 1),
                         tf.expand_dims(centroids, 0))

    clusters = \
        tf.argmin(distances, 1)

    def select(n):
        indices = tf.reshape(tf.where(tf.equal(clusters, n)), [1, -1])
        return tf.gather(vectors, indices)

    new_centroids = \
        tf.concat(0, [tf.reduce_mean(select(i), 1) for i in range(k)])

    update_centroids = \
        tf.assign(centroids, new_centroids)

    def plot(points):
        plt.plot(points[:, 0], points[:, 1], 'o')

    def draw():
        for points in [session.run(select(i))[0] for i in range(k)]:
            plot(points)
        plot(session.run(centroids))
        plt.show()

    session = tf.Session()
    session.run(tf.initialize_all_variables())

    draw()
    for i in range(2):
        for j in range(3):
            session.run(update_centroids)
        draw()

if __name__ == '__main__':
    main()
