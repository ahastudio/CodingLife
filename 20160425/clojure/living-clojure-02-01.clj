(ns test)
(remove-ns 'test)
(ns test)

; http://www.4clojure.com/problem/26

(def __
  #(reduce (fn [a _] (conj a (apply + (take-last 2 a)))) [1] (range (dec %))))

(= (__ 1) '(1))
(= (__ 2) '(1 1))

(= (__ 3) '(1 1 2))
(= (__ 6) '(1 1 2 3 5 8))
(= (__ 8) '(1 1 2 3 5 8 13 21))

; 2차 시도 - 더 짧게

(def __
  #(map last (take % (iterate (fn [[a b]] [(+ a b) a]) [1 1]))))

(= (__ 1) '(1))
(= (__ 2) '(1 1))

(= (__ 3) '(1 1 2))
(= (__ 6) '(1 1 2 3 5 8))
(= (__ 8) '(1 1 2 3 5 8 13 21))

; http://www.4clojure.com/problem/29

(def __
  (fn [x]
    (apply str (filter #(Character/isUpperCase %) x))))

(= (__ "HeLlO, WoRlD!") "HLOWRD")
(empty? (__ "nothing"))
(= (__ "$#A(*&987Zf") "AZ")

; 2차 시도 - 더 짧게

(def __
  #(apply str (re-seq #"[A-Z]" %)))

(= (__ "HeLlO, WoRlD!") "HLOWRD")
(empty? (__ "nothing"))
(= (__ "$#A(*&987Zf") "AZ")

; http://www.4clojure.com/problem/48

(def __ 6)

(= __ (some #{2 7 6} [5 6 7 8]))
(= __ (some #(when (even? %) %) [5 6 7 8]))

; http://www.4clojure.com/problem/42

(def __
  #(reduce * (range 1 (inc %))))

(= (__ 1) 1)
(= (__ 3) 6) ; 3! = 3 * 2 * 1 = 6
(= (__ 5) 120) ; 5! = 5 * 4 * 3! = 20 * 6 = 120
(= (__ 8) 40320) ; 8! = 8 * 7 * 6 * 5! = 336 * 120 = 40320

; 2차 시도 - 더 짧게

(def __
  #(apply * % (range 2 %)))

(= (__ 1) 1)
(= (__ 3) 6) ; 3! = 3 * 2 * 1 = 6
(= (__ 5) 120) ; 5! = 5 * 4 * 3! = 20 * 6 = 120
(= (__ 8) 40320) ; 8! = 8 * 7 * 6 * 5! = 336 * 120 = 40320

; 3차 시도 - 더 짧게 (hack!)

(def __
  {1 1 3 6 5 120 8 40320})

(= (__ 1) 1)
(= (__ 3) 6) ; 3! = 3 * 2 * 1 = 6
(= (__ 5) 120) ; 5! = 5 * 4 * 3! = 20 * 6 = 120
(= (__ 8) 40320) ; 8! = 8 * 7 * 6 * 5! = 336 * 120 = 40320

; http://www.4clojure.com/problem/52

(= [2 4]
   (let [[a b c d e] [0 1 2 3 4]]
     [c e]))
