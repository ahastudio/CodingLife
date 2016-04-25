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

; 책에서 다루지 않은 문제

; http://www.4clojure.com/problem/38

(def __
  (fn [& a]
    (reduce #(if (> % %2) % %2) a)))

(= (__ 1 8 3 4) 8)
(= (__ 30 20) 30)
(= (__ 45 67 11) 67)

; 2차 시도 - 더 짧게

(def __
  (fn [& a]
    (last (sort a))))

(= (__ 1 8 3 4) 8)
(= (__ 30 20) 30)
(= (__ 45 67 11) 67)

; 3차 시도 - 더 짧게

(def __
  #(last (sort %&)))

(= (__ 1 8 3 4) 8)
(= (__ 30 20) 30)
(= (__ 45 67 11) 67)

; http://www.4clojure.com/problem/34

(def __
  #(take (- %2 %) (iterate inc %)))

(= (__ 1 4) '(1 2 3))
(= (__ -2 2) '(-2 -1 0 1))
(= (__ 5 8) '(5 6 7))

; http://www.4clojure.com/problem/39

(def __ interleave)

(= (__ [1 2 3] [:a :b :c])
   '(1 :a 2 :b 3 :c))
(= (__ [1 2] [3 4 5 6])
   '(1 3 2 4))
(= (__ [1 2 3 4] [5])
   [1 5])
(= (__ [30 20] [25 15])
   [30 25 20 15])

; 1차 풀이

(def __
  #(flatten (map vector % %2)))

(= (__ [1 2 3] [:a :b :c])
   '(1 :a 2 :b 3 :c))
(= (__ [1 2] [3 4 5 6])
   '(1 3 2 4))
(= (__ [1 2 3 4] [5])
   [1 5])
(= (__ [30 20] [25 15])
   [30 25 20 15])

; 2차 풀이 - 더 짧게

(def __
  #(mapcat vector % %2))

(= (__ [1 2 3] [:a :b :c])
   '(1 :a 2 :b 3 :c))
(= (__ [1 2] [3 4 5 6])
   '(1 3 2 4))
(= (__ [1 2 3 4] [5])
   [1 5])
(= (__ [30 20] [25 15])
   [30 25 20 15])

; 3차 풀이 - 더 짧게

(def __
  #(mapcat list % %2))

(= (__ [1 2 3] [:a :b :c])
   '(1 :a 2 :b 3 :c))
(= (__ [1 2] [3 4 5 6])
   '(1 3 2 4))
(= (__ [1 2 3 4] [5])
   [1 5])
(= (__ [30 20] [25 15])
   [30 25 20 15])

; http://www.4clojure.com/problem/40

(def __ interpose)

(= (__ 0 [1 2 3])
   [1 0 2 0 3])
(= (apply str (__ ", " ["one" "two" "three"]))
   "one, two, three")
(= (__ :z [:a :b :c :d])
   [:a :z :b :z :c :z :d])

; 1차 풀이

(def __
  #(drop-last (mapcat list %2 (repeat %))))

(= (__ 0 [1 2 3])
   [1 0 2 0 3])
(= (apply str (__ ", " ["one" "two" "three"]))
   "one, two, three")
(= (__ :z [:a :b :c :d])
   [:a :z :b :z :c :z :d])

; 1차 풀이 - 더 짧게

(def __
  #(rest (mapcat list (repeat %) %2)))

(= (__ 0 [1 2 3])
   [1 0 2 0 3])
(= (apply str (__ ", " ["one" "two" "three"]))
   "one, two, three")
(= (__ :z [:a :b :c :d])
   [:a :z :b :z :c :z :d])

; 2차 풀이 - 더 짧게

(def __
  #(rest (interleave (repeat %) %2)))

(= (__ 0 [1 2 3])
   [1 0 2 0 3])
(= (apply str (__ ", " ["one" "two" "three"]))
   "one, two, three")
(= (__ :z [:a :b :c :d])
   [:a :z :b :z :c :z :d])
