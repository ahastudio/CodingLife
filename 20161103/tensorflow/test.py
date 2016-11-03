# -*- coding: utf-8 -*-

import csv
import tensorflow as tf
import matplotlib.pyplot as plt

def read_data():
    # 데이터 출처: http://analyticsstory.com/98
    file = open('data.csv')
    reader = csv.reader(file)
    rows = [row[1:] for row in reader][1:]
    return [float(row[0]) for row in rows], [float(row[1]) for row in rows]

def main():
    real_x_data, real_y_data = read_data()
    x_scale = max(real_x_data + [1.0])
    y_scale = max(real_y_data + [1.0])
    x_data = [x / x_scale for x in real_x_data]
    y_data = [y / y_scale for y in real_y_data]

    w = tf.Variable(0.0)
    b = tf.Variable(0.5)
    y = w * x_data + b

    cost = tf.reduce_mean(tf.square(y_data - y))

    optimizer = tf.train.GradientDescentOptimizer(tf.Variable(0.3))
    train = optimizer.minimize(cost)

    session = tf.Session()
    session.run(tf.initialize_all_variables())

    def get_y(real_x):
        return (session.run(w) * (real_x / x_scale) + session.run(b)) * y_scale

    xs = range(50, 200, 5)
    ys_list = [map(get_y, xs)]

    for i in range(20):
        for j in range(10):
            session.run(train)
        ys_list.append(map(get_y, xs))

    plt.plot(real_x_data, real_y_data, 'ro')
    for ys in ys_list[:-1]:
        plt.plot(xs, ys, 'g')
    plt.plot(xs, ys_list[-1], 'bo')
    plt.show()

if __name__ == '__main__':
    main()
