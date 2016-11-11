# -*- coding: utf-8 -*-

import csv
import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt

def create_data():
    return np.concatenate((np.random.standard_normal((500, 2)) * 3 + [10, 9],
                           np.random.standard_normal((300, 2)) * 2 + [2, 3]))

def read_data():
    # https://vincentarelbundock.github.io/Rdatasets/datasets.html
    file = open('xclara.csv')
    reader = csv.reader(file)
    data = np.array(list(reader))
    data[data == ''] = 0
    labels = list(data[0])
    indices = (labels.index('V1'), labels.index('V2'))
    return data[1:, indices].astype(np.float64)

def run(data):
    k = 3

    scale = np.array([max(list(np.absolute(data[:, 0])) + [1.0]),
                      max(list(np.absolute(data[:, 1])) + [1.0])])

    indices = np.arange(data.shape[0])
    np.random.shuffle(indices)
    centers = tf.Variable(data[indices[:k]] / scale)
    points = tf.constant(data / scale)

    exp_points = tf.expand_dims(points, 0)
    exp_centers = tf.expand_dims(centers, 1)
    distances = tf.reduce_sum(tf.square(exp_points - exp_centers), 2)
    clusters = tf.argmin(distances, 0)

    def cluster_points(index):
        cluster_condition = tf.where(tf.equal(clusters, index))
        cluster_indices = tf.reshape(cluster_condition, [1, -1])
        return tf.gather(points, cluster_indices)

    partitions = [cluster_points(i) for i in range(k)]
    means = tf.concat(0, [tf.reduce_mean(p, 1) for p in partitions])
    update_centers = tf.assign(centers, means)

    session = tf.Session()
    session.run(tf.initialize_all_variables())

    def draw():
        for [xys], index in zip(session.run(partitions), range(k)):
            plt.plot(xys[:, 0], xys[:, 1], 'o')
        plt.show()

    draw()
    for i in range(5):
        session.run(update_centers)
    draw()

def main():
    # run(create_data())
    run(read_data())

if __name__ == '__main__':
    main()
