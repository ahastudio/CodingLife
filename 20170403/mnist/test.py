import numpy as np

with open('data/t10k-images-idx3-ubyte', 'rb') as f:
    x_data = np.frombuffer(f.read(), np.uint8, offset=16).reshape(-1, 28 * 28)
with open('data/t10k-labels-idx1-ubyte', 'rb') as f:
    y_data = np.frombuffer(f.read(), np.uint8, offset=8)

print(x_data.shape)
print(y_data.shape)
