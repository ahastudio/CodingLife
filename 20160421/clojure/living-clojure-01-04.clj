(ns test)
(remove-ns 'test)
(ns test)

; http://www.4clojure.com/problem/20

(def f
  #(->> % drop-last last))

(= (f (list 1 2 3 4 5)) 4)

(= (f ["a" "b" "c"]) "b")

(= (f [[1 2] [3 4]]) [1 2])

; http://www.4clojure.com/problem/24

(def f
  #(apply + %))

(= (f [1 2 3]) 6)

(= (f (list 0 -2 5 5)) 8)

(= (f #{4 2 1}) 7)

(= (f '(0 0 -1)) -1)

; http://www.4clojure.com/problem/25

(def f
  #(filter odd? %))

(= (f #{1 2 3 4 5}) '(1 3 5))

(= (f [4 2 1 6]) '(1))

(= (f [2 2 4 6]) '())

(= (f [1 1 1 3]) '(1 1 1 3))

; http://www.4clojure.com/problem/27

(def f
  #(= (seq %) (reverse %)))

(false? (f '(1 2 3 4 5)))

(true? (f "racecar"))

(true? (f [:foo :bar :foo]))

(true? (f '(1 1 3 3 1 1)))

(false? (f '(:a :b :c)))

; http://www.4clojure.com/problem/32

(def f
  (partial reduce #(conj %1 %2 %2) []))

(= (f [1 2 3]) '(1 1 2 2 3 3))

(= (f [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))

(= (f [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

(= (f [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

; 2차 - 더 짧게!

(def f
  (partial mapcat #(vector % %)))

(= (f [1 2 3]) '(1 1 2 2 3 3))

(= (f [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))

(= (f [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

(= (f [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

; 3차 - 더 짧게!

(mapcat #(vector % %)
        [1 2 3])

(= (mapcat #(vector % %) [1 2 3]) '(1 1 2 2 3 3))

(= (mapcat #(vector % %) [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))

(= (mapcat #(vector % %) [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

(= (mapcat #(vector % %) [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
