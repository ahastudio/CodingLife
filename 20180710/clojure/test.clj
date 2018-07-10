(ns test)
(remove-ns 'test)
(ns test)

; funcions ---

(defn d [n]
  (loop [a n
         x n]
    (if (= x 0)
      a
      (recur (+ a (mod x 10))
             (quot x 10)))))

(defn self-numbers [n]
  (clojure.set/difference
    (set (range 1 n))
    (set (map d (range 1 n)))))

; test ---

; 101 = 9 + 1 + 91 = 1 + 0 + 0 + 100
(d 91)
(d 100)

; 1 3 5 7 9 20 31
(self-numbers 40)

; solve it!
(apply + (self-numbers 5000))
