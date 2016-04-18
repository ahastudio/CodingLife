(ns test)
(remove-ns 'test)
(ns test)

; http://www.4clojure.com/problem/1

(= true
   true)

; http://www.4clojure.com/problem/2

(= (- 10 (* 2 3))
   4)

; http://www.4clojure.com/problem/3

(= "HELLO WORLD"
   (.toUpperCase "hello world"))

; http://www.4clojure.com/problem/4

(= (list :a :b :c)
   '(:a :b :c))

; http://www.4clojure.com/problem/5

(= '(1 2 3 4)
   (conj '(2 3 4) 1))

(= '(1 2 3 4)
   (conj '(3 4) 2 1))

; http://www.4clojure.com/problem/6

(= [:a :b :c]
   (list :a :b :c)
   (vec '(:a :b :c))
   (vector :a :b :c))

; http://www.4clojure.com/problem/7

(= [1 2 3 4]
   (conj [1 2 3] 4))

(= [1 2 3 4]
   (conj [1 2] 3 4))

; http://www.4clojure.com/problem/8

(= #{:a :b :c :d}
   (set '(:a :a :b :c :c :c :c :d :d)))

(= #{:a :b :c :d}
   (clojure.set/union #{:a :b :c} #{:b :c :d}))

; http://www.4clojure.com/problem/9

(= #{1 2 3 4}
   (conj #{1 4 3} 2))

; http://www.4clojure.com/problem/10

(= 20
   ((hash-map :a 10, :b 20, :c 30) :b))


(= 20
   (:b {:a 10, :b 20, :c 30}))

; http://www.4clojure.com/problem/11

(= {:a 1, :b 2, :c 3}
   (conj {:a 1}
         [:b 2]
         [:c 3]))

; http://www.4clojure.com/problem/12

(= 3
   (first '(3 2 1)))

(= 3
   (second [2 3 4]))

(= 3
   (last (list 1 2 3)))
