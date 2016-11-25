import tensorflow as tf

def tree(x):
    if len(x) <= 1:
        return tf.constant(x)
    l, r = x[:len(x) / 2], x[len(x) / 2:]
    return tf.add(tree(l), tree(r))

op = tree(range(1, 101))
session = tf.Session()
print session.run(op)
