(ns test)
(remove-ns 'test)
(ns test)

; 4장 - 조건문과 함수

; 연습문제 4.1.1

(and (> 4 3) (<= 10 100))

(or (> 4 3) (= 10 100))

(not (= 2 3))

; 연습문제 4.1.2

(map (fn [x]
       [(> x 3)
        (and (> 4 x) (> x 3))
        (= (* x x) x)])
     [4, 2, (/ 7 2)])

; 연습문제 4.2.1

(defn is-between-3-7? [n]
  (and (< 3 n) (<= n 7)))

(is-between-3-7? 1)
(is-between-3-7? 3)
(is-between-3-7? 5)
(is-between-3-7? 7)
(is-between-3-7? 9)

; 위에서 정의한 프로시저와 이름이 같음 ㅠㅠ
(defn is-between-3-7? [n]
  (and (<= 3 n) (<= n 7)))


(is-between-3-7? 1)
(is-between-3-7? 3)
(is-between-3-7? 5)
(is-between-3-7? 7)
(is-between-3-7? 9)

(defn is-between-3-9? [n]
  (and (<= 3 n) (< n 9)))


(is-between-3-9? 1)
(is-between-3-9? 3)
(is-between-3-9? 5)
(is-between-3-9? 9)
(is-between-3-9? 10)

(defn is-between-1-3-or-9-11? [n]
  (or (and (< 1 n) (< n 3))
      (and (< 9 n) (< n 11))))

(is-between-1-3-or-9-11? 1)
(is-between-1-3-or-9-11? 2)
(is-between-1-3-or-9-11? 3)
(is-between-1-3-or-9-11? 6)
(is-between-1-3-or-9-11? 9)
(is-between-1-3-or-9-11? 10)
(is-between-1-3-or-9-11? 11)

(defn is-not-between-1-3? [n]
  (not (and (<= 1 n) (<= n 3))))

(is-not-between-1-3? 0)
(is-not-between-1-3? 1)
(is-not-between-1-3? 2)
(is-not-between-1-3? 3)
(is-not-between-1-3? 4)

; 연습문제 4.2.2

(defn in-interval-1? [x]
  (and (< -3 x) (< x 0)))

(defn in-interval-2? [x]
  (or (< x 1) (> x 2)))

(defn in-interval-3? [x]
  (not (and (<= 1 x) (<= x 5))))

(in-interval-1? -2)
(in-interval-2? -2)
(in-interval-3? -2)

; 연습문제 4.2.3

(defn equation1 [x]
  (= (+ (* x x)
        (+ (* 2 x)
           1))
     0))

(equation1 -1)
(equation1 +1)

(defn equation2 [n]
  (= (+ (* 4 n)
        2)
     62))

(equation2 -1)
(equation2 +1)

(defn equation3 [n]
  (= (* 2 (* n n))
     102))

(equation3 -1)
(equation3 +1)

(defn equation4 [n]
  (= (+ (* 4 (* n n))
        (* 6 n)
        2)
     462))

(equation4 -1)
(equation4 +1)


; 연습문제 4.2.4

(defn Fahrenheit->Celsius [fahrenheit]
  (/ (- fahrenheit 32) 1.8))

(= (Fahrenheit->Celsius 32)
   0.0)

(defn dollar->euro [dollar]
  (* 0.88 dollar))

(= (dollar->euro 100)
   88.0)

(defn triangle [base height]
  (/ (* base height) 2))

(= (triangle 10 10)
   50)

(defn convert3 [a b c]
  (+ a (* b 10) (* c 100)))

(= (convert3 1 2 3)
   321)
