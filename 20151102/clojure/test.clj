(ns test)
(remove-ns 'test)
(ns test)

; Currying 예제

(defn add [a b]
  (+ a b))

(add 1 2)

((partial add 1) 2)

(def inc (partial add 1))

(inc 2)

; FP in Scala 연습문제 10.5 foldMap

(defn fold-map [f op zero list]
  (reduce #(op %1 (f %2)) zero list))

(defn op [a b]
  (str a b))

(def zero "")

(fold-map #(str %) op zero [1 2 3])

; FP in Scala 연습문제 10.6 fold-left

(defn op [a b]
  #(a (b %)))

(def zero (fn [x] x))

(let [a #(+ % 3)
      b #(+ % 8)
      c #(- % 3)
      x 10]
  (= ((op (op a b) c) x)
     ((op a (op b c)) x)))

(let [a #(+ % 3)
      x 10]
  (= ((op zero a) x)
     ((op a zero) x)))

(defn fold-left [f init coll]
  ((fold-map #(partial f %)
             op
             zero
             coll) init))

(= "abc."
   (fold-left str "." ["a" "b" "c"]))

(= (reduce str "." ["a" "b" "c"])
   (fold-left str "." ["a" "b" "c"]))

(defn fold-right [f init coll]
  ((fold-map (fn [x] #(f % x))
             #(op %2 %1)
             zero
             coll) init))

(fold-right str "." ["a" "b" "c"])

(= ".abc"
   (fold-right str "." ["a" "b" "c"]))
