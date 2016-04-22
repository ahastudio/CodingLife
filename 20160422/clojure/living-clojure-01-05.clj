(ns test)
(remove-ns 'test)
(ns test)

; http://www.4clojure.com/problem/30

(def __
  (partial reduce
           #(if (not= (last %1) %2)
              (conj %1 %2)
              %1)
           []))

(= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
(= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

; 2차 시도 - 더 짧게

(def __
  #(->> % (partition-by list) (map last)))

(= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
(= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

; 3차 시도 - 더 짧게

(def __
  #(map last (partition-by list %)))

(= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
(= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

; http://www.4clojure.com/problem/31

(def __
  (partial partition-by list))

(= (__ [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (__ [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (__ [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

; 2차 시도 - 더 짧게

(def __
  (partial partition-by max))

(= (__ [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (__ [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (__ [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

; http://www.4clojure.com/problem/41

(def __
  #(mapcat (partial take (dec %2)) (partition-all %2 %1)))

(= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (__ [:a :b :c :d :e :f] 2) [:a :c :e])
(= (__ [1 2 3 4 5 6] 4) [1 2 3 5 6])

; 2차 시도 - 더 짧게

(def __
  #(apply concat (partition-all (dec %2) %2 %1)))

(= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (__ [:a :b :c :d :e :f] 2) [:a :c :e])
(= (__ [1 2 3 4 5 6] 4) [1 2 3 5 6])

; 3차 시도 - 더 짧게

(def __
  #(flatten (partition-all (dec %2) %2 %)))

(= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (__ [:a :b :c :d :e :f] 2) [:a :c :e])
(= (__ [1 2 3 4 5 6] 4) [1 2 3 5 6])

; 4차 시도 - 더 짧게

(def __
  #(flatten (partition (dec %2) %2 [] %)))

(= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (__ [:a :b :c :d :e :f] 2) [:a :c :e])
(= (__ [1 2 3 4 5 6] 4) [1 2 3 5 6])

; http://www.4clojure.com/problem/45

(= [1 4 7 10 13]
   (take 5 (iterate #(+ 3 %) 1)))

; 2차 시도 - 더 짧아지지 않...

(= (range 1 14 3)
   (take 5 (iterate #(+ 3 %) 1)))

; http://www.4clojure.com/problem/33

(def __
  #(mapcat (partial repeat %2) %))

(= (__ [1 2 3] 2) '(1 1 2 2 3 3))
(= (__ [:a :b] 4) '(:a :a :a :a :b :b :b :b))
(= (__ [4 5 6] 1) '(4 5 6))
(= (__ [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
(= (__ [44 33] 2) [44 44 33 33])

; 책에서 하라고 하지 않은...

; http://www.4clojure.com/problem/28

(def __
  flatten)

(= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
(= (__ '((((:a))))) '(:a))

; Special Restrictions: flatten

((fn f [a b]
   (if (sequential? b)
     (reduce #(concat % (f a %2)) a b)
     (conj a b)))
 []
 '((1 2) 3 [4 [5 6]]))

; 2차 시도 - 더 짧게

(def __
  (fn f [x]
    (mapcat #(if (coll? %) (f %) [%])
            x)))

(= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
(= (__ '((((:a))))) '(:a))

; http://www.4clojure.com/problem/195
; Parentheses... Again 문제
; Ruby 코드를 그대로 옮김: https://github.com/ahastudio/CodingLife/tree/master/20160422/ruby

(def __
  (fn f [n]
    (set
      (if (= n 0)
        [""]
        (mapcat #(for [i (f %) j (f (- n % 1))]
                   (str "(" i ")" j))
                (range n))))))

(= [#{""} #{"()"} #{"()()" "(())"}]
   (map (fn [n] (__ n))
        [0 1 2]))

(= #{"((()))" "()()()" "()(())" "(())()" "(()())"}
   (__ 3))

(= 16796
   (count (__ 10)))

(= (nth (sort (filter #(.contains ^String % "(()()()())")
                      (__ 9)))
        6)
   "(((()()()())(())))")

(= (nth (sort (__ 12))
        5000)
   "(((((()()()()()))))(()))")
