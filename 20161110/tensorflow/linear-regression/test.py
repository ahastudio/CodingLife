# -*- coding: utf-8 -*-

import csv
import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt

def read_data():
    # 데이터 출처: http://analyticsstory.com/98
    file = open('data.csv')
    reader = csv.reader(file)
    data = np.array(list(reader))
    labels = list(data[0])
    x_data = data[1:, labels.index('height')].astype(np.float64)
    y_data = data[1:, labels.index('weight')].astype(np.float64)
    return x_data, y_data

def f(x):
    return 3 * x - 10

def create_data():
    x_data = np.random.random_sample([1000]) * 100
    y_data = f(x_data) + \
             np.random.normal(0, 100.0, 1000)
    return x_data, y_data

def run(real_x_data, real_y_data):
    x_scale = max(list(np.absolute(real_x_data)) + [1.0])
    y_scale = max(list(np.absolute(real_y_data)) + [1.0])
    x_data = real_x_data / x_scale
    y_data = real_y_data / y_scale

    w = tf.Variable(np.random.random_sample() * 2 - 1)
    b = tf.Variable(np.random.random_sample() * 2 - 1)
    y = w * x_data + b

    cost = tf.reduce_mean(tf.square(y_data - y))

    optimizer = tf.train.GradientDescentOptimizer(tf.Variable(0.3))
    train = optimizer.minimize(cost)

    session = tf.Session()
    session.run(tf.initialize_all_variables())

    def get_real_ys(real_xs):
        return (session.run(w) * (real_xs / x_scale) + session.run(b)) * y_scale

    xs = range(min(real_x_data.astype(int)) - 10,
               max(real_x_data.astype(int)) + 10,
               5)
    ys_list = [get_real_ys(xs)]

    for i in range(20):
        for j in range(10):
            session.run(train)
        ys_list.append(get_real_ys(xs))

    print(session.run(w), session.run(b))

    plt.plot(real_x_data, real_y_data, 'ro')
    for ys in ys_list[:-1]:
        plt.plot(xs, ys, 'g')
    plt.plot(xs, ys_list[-1], 'bo')
    plt.show()

def main():
    x_data, y_data = create_data()
    run(x_data, y_data)
    x_data, y_data = read_data()
    run(x_data, y_data)

if __name__ == '__main__':
    main()
