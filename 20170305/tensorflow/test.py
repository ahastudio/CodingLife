import tensorflow as tf
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import LabelBinarizer
from sklearn.model_selection import train_test_split

df = pd.read_csv('iris.csv')

df.columns = [x.lower().replace('.', '_') for x in df.columns]

labels, uniques = pd.factorize(df.species)
df['label'] = labels

train, test = train_test_split(df, test_size = 0.2)

def column(df, name):
    m = df.as_matrix([name])
    return (m - m.min()) / m.ptp()

names = ['sepal_length', 'sepal_width', 'petal_length', 'petal_width']
all_features = np.hstack([column(df, i) for i in names])
train_features = np.hstack([column(train, i) for i in names])
test_features = np.hstack([column(test, i) for i in names])

lb = LabelBinarizer()
lb.fit(df.label)

all_labels = lb.transform(df.label).astype('float')
train_labels = lb.transform(train.label).astype('float')
test_labels = lb.transform(test.label).astype('float')

# Softmax Regression

k = lb.classes_.size

x = tf.placeholder(tf.float32, [None, 4])
y = tf.placeholder(tf.float32, [None, 3])

w = tf.Variable(tf.truncated_normal([4, k], stddev=0.1))
b = tf.Variable(tf.truncated_normal([k], stddev=0.1))
y_ = tf.nn.softmax(tf.matmul(x, w) + b)

# Loss Function

cross_entropy = -tf.reduce_sum(y * tf.log(y_), axis=1)
loss = tf.reduce_mean(cross_entropy)

# Gradient Descent Optimization

learning_rate = 0.5
optimizer = tf.train.GradientDescentOptimizer(learning_rate)

# Initialize

session = tf.InteractiveSession()
tf.global_variables_initializer().run()

# Train & Test

all_data = {x: all_features, y: all_labels}
train_data = {x: train_features, y: train_labels}
test_data = {x: test_features, y: test_labels}

correct_prediction = tf.equal(tf.argmax(y, 1), tf.argmax(y_, 1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))

for i in range(10):
    print(i)
    for i in range(20):
        optimizer.minimize(loss).run(feed_dict=train_data)
    print(session.run(accuracy, feed_dict=train_data))
    print(session.run(accuracy, feed_dict=test_data))
    print('-------------------------------------------')

labels = session.run(tf.argmax(y_, 1), feed_dict=all_data)
df.plot.scatter('sepal_length', 'petal_length',
                c=pd.Series(['r', 'g', 'b'])[labels])
plt.show()
