(ns test)
(remove-ns 'test)
(ns test)

; 1.1 팩토리얼 계산하기

(defn factorial [n]
  (reduce * (range 1 (+ n 1))))

  (= (* 1 2 3 4 5 6 7 8 9 10)
   (factorial 10))

(defn factorial2 [n]
  (if (= n 1)
    1
    (* n
       (factorial2 (- n 1)))))

(= (* 1 2 3 4 5 6 7 8 9 10)
   (factorial2 10))

(defn factorial3 [n]
  (defn iter [a n]
    (if (= n 1)
      a
      (iter (*' a n) (- n 1))))
  (iter 1 n))

(= (* 1 2 3 4 5 6 7 8 9 10)
   (factorial3 10))

(factorial3 100)

(defn factorial4 [n]
  (loop [a 1
         n n]
    (if (= n 1)
      a
      (recur (*' a n) (- n 1)))))

(= (* 1 2 3 4 5 6 7 8 9 10)
   (factorial4 10))

(factorial4 100)
(factorial4 10000)
