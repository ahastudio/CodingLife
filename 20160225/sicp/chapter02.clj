(ns test)
(remove-ns 'test)
(ns test)

; 연습문제 2.25

(let [x '(1 3 (5 7) 9)]
  (first (next (first (next (next x))))))

(let [x '((7))]
  (first (first x)))

(let [x '(1 (2 (3 (4 (5 (6 7))))))]
  (second (second (second (second (second (second x)))))))

; 연습문제 2.26

(def x (list 1 2 3))
(def y (list 4 5 6))

(concat x y)
(cons x y)
(list x y)

; 연습문제 2.27

(defn reverse [items]
  (if (empty? items)
    '()
    (concat (reverse (next items))
            [(first items)])))

(reverse (list 1 4 9 16 25))

(defn deep-reverse [items]
  (if (list? items)
    (concat (deep-reverse (next items))
            [(deep-reverse (first items))])
    items))

(deep-reverse 1)
(deep-reverse (list 1 2))

(def x (list (list 1 2) (list 3 4)))

x
(reverse x)
(deep-reverse x)

; 연습문제 2.28

(defn fringe [node]
  (if (list? node)
    (concat (fringe (first node))
            (fringe (second node)))
    (list node)))

(fringe (list 1 2))

(def x (list (list 1 2) (list 3 4)))

(fringe x)
(fringe (list x x))
