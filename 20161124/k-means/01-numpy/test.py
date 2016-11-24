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
    vectors = data

    indices = np.arange(len(data))
    np.random.shuffle(indices)
    centroids = data[indices[:k]]

    def distance(a, b):
        return np.sum((a - b) ** 2, axis=1)

    def distances():
        return [distance(vectors, centroids[i]) for i in range(k)]

    def clusters():
        return np.argmin(distances(), axis=0)

    def select(n):
        indices = np.where(np.equal(clusters(), n))
        return vectors[indices]

    def new_centroids():
        return [np.mean(select(i), axis=0) for i in range(k)]

    def update_centroids():
        centroids[:] = new_centroids()

    def plot(points):
        plt.plot(points[:, 0], points[:, 1], 'o')

    def draw():
        for points in [select(i) for i in range(k)]:
            plot(points)
        plot(centroids)
        plt.show()

    draw()
    for i in range(2):
        for j in range(3):
            update_centroids()
        draw()

if __name__ == '__main__':
    main()
