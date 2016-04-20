(ns test)
(remove-ns 'test)
(ns test)

; 정규식(Regular Expressions)
; http://www.4clojure.com/problem/37

(= "ABC"
   (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))

(re-seq #"[A-Z]+" "bA1B3Ce ")

; http://www.4clojure.com/problem/57

(= '(5 4 3 2 1)
   ((fn foo [x]
      (when (> x 0) (conj (foo (dec x)) x)))
    5)
   )

(defn foo [x]
  (if (> x 0)
    (conj (foo (dec x))
          x)
    nil))
(foo 5)

(conj nil 10)

; http://www.4clojure.com/problem/68

(= [7 6 5 4 3]
  (loop [x 5
         result []]
    (if (> x 0)
      (recur (dec x) (conj result (+ 2 x)))
      result)))

; 코드 재배열: -> (Rearranging Code: ->)
; http://www.4clojure.com/problem/71
; Thread-first Macro: ->

(.toUpperCase (str (first [:cat :dog :fish])))

(-> [:cat :dog :fish] first str .toUpperCase)

(= (last (sort (rest (reverse [2 5 4 1 3 6]))))
   (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (last))
   5)

; 코드 재배열: ->> (Rearranging Code: ->>)
; http://www.4clojure.com/problem/72
; Thread-last Macro: ->>

(->> [1 2 3 4 5 6 7 8] (filter even?) (take 3))

(= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
   11)

; http://www.4clojure.com/problem/145

(def result
  (map #(inc (* 4 %)) (range 10)))

result

; 나머지(remainder) - https://clojuredocs.org/clojure.core/rem
(rem 0 4)
(rem 1 4)
(rem 2 4)
(rem 3 4)
(rem 4 4)
(rem 5 4)
(rem 6 4)
(rem 7 4)
(rem 8 4)

(= result
   (for [x (iterate #(+ 4 %) 0)
         :let [z (inc x)]
         :while (< z 40)]
     z))

(iterate #(+ 4 %) 0)

(= result
   (for [[x y] (partition 2 (range 20))]
     (+ x y)))
