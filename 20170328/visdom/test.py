import visdom
import numpy as np

vis = visdom.Visdom()

x = np.array([1, 2, 3, 4, 5, 6])
y = x ** 2 + 3
vis.scatter(np.stack([x, y], axis=1))

z = x * 3
vis.scatter(np.stack([x, y, z], axis=1))
