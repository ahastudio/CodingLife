(ns test)
(remove-ns 'test)
(ns test)

; http://www.4clojure.com/problem/51

(def __
  [1 2 3 4 5])

(= [1 2 [3 4 5] [1 2 3 4 5]]
   (let [[a b & c :as d] __]
     [a b c d]))

; http://www.4clojure.com/problem/83

(def __
  #(= #{true false} (set %&)))

(= false (__ false false))
(= true (__ true false))
(= false (__ true))
(= true (__ false true false))
(= false (__ true true true))
(= true (__ true true true false))

; 2차 풀이 - 더 짧게

(def __
  not=)

(= false (__ false false))
(= true (__ true false))
(= false (__ true))
(= true (__ false true false))
(= false (__ true true true))
(= true (__ true true true false))

; http://www.4clojure.com/problem/66

(def __
  #(loop [a (min % %2)]
     (if (and (= 0 (rem % a))
              (= 0 (rem %2 a)))
       a
       (recur (dec a)))))

(= (__ 2 4) 2)
(= (__ 10 5) 5)
(= (__ 5 7) 1)
(= (__ 1023 858) 33)

; 2차 풀이 - 더 짧게

(def __
  (fn f [x y]
    (if (= 0 (rem x y))
      y
      (f y (rem x y)))))

(= (__ 2 4) 2)
(= (__ 10 5) 5)
(= (__ 5 7) 1)
(= (__ 1023 858) 33)

; 3차 풀이 - 더 짧게

(def __
  #(if (= 0 (rem %2 %))
     %
     (recur (rem %2 %) %)))

(= (__ 2 4) 2)
(= (__ 10 5) 5)
(= (__ 5 7) 1)
(= (__ 1023 858) 33)
