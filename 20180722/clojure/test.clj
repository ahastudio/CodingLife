(ns test)
(remove-ns 'test)
(ns test)

; funcions ---

(defn defferences [n]
  (map (fn [[a b]] (Math/abs (- a b)))
       (map vector n (drop 1 n))))

(defn jolly? [& n]
  (= (set (range 1 (count n)))
     (set (defferences n))))

; test ---

(= true (jolly? 1 4 2 3))
(= false (jolly? 1 4 2 -1 6))
(= true (jolly? 11 7 4 2 1 6))

(defferences [1 2 3])
(defferences [1 2 4])
(defferences [2 1 3])
