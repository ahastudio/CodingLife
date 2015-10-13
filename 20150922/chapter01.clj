(ns test)
(remove-ns 'test)
(ns test)

; 1.2.2 여러 갈래로 되도는 프로세스 / Tree Recursion (p.48)

(defn fib [n]
  (cond (= n 0) 0
        (= n 1) 1
        :else (+ (fib (- n 1))
                 (fib (- n 2)))))

(fib 0)
(fib 1)
(fib 2)
(fib 3)
(fib 4)
(fib 5)
(fib 6)
(fib 7)
(fib 8)
(fib 9)
(fib 10)

(map fib (range 11))

(defn fib-iter [a b count]
  (if (= count 0)
    b
    (fib-iter (+ a b) a (- count 1))))

(defn fib [n]
  (fib-iter 1 0 n))

(map fib (range 11))

; 연습: 돈 바꾸는 방법

(defn first-denomination [kinds-of-coins]
  (cond (= kinds-of-coins 1) 1
        (= kinds-of-coins 2) 5
        (= kinds-of-coins 3) 10
        (= kinds-of-coins 4) 25
        (= kinds-of-coins 5) 50))

(defn cc [amount kinds-of-coins]
  (cond (= amount 0) 1
        (or (< amount 0) (= kinds-of-coins 0)) 0
        :else (+ (cc amount
                     (- kinds-of-coins 1))
                 (cc (- amount
                        (first-denomination kinds-of-coins))
                     kinds-of-coins))))


(defn count-change [amount]
  (cc amount 5))

(count-change 100)

; 연습문제 1.11

(defn f [n]
  (if (< n 3)
    n
    (+ (f (- n 1))
       (* 2 (f (- n 2)))
       (* 3 (f (- n 3))))))

(map f [0 1 2 3 4 5 6 7 8 9 10])

(defn f [n]
  (defn iter [a b c count]
    (if (= count 0)
      a
      (iter b
            c
            (+ c (* 2 b) (* 3 a))
            (- count 1))))
  (iter 0 1 2 n))

(map f [0 1 2 3 4 5 6 7 8 9 10])

; 연습문제 1.12

(defn pascals-triangle [x y]
  (if (or (= y 0) (= x 0) (= x y))
    1
    (+ (pascals-triangle (- x 1) (- y 1))
       (pascals-triangle x (- y 1)))))

(pascals-triangle 0 0)
(map (fn [x] (pascals-triangle x 1)) (range 2))
(map (fn [x] (pascals-triangle x 2)) (range 3))
(map (fn [x] (pascals-triangle x 3)) (range 4))
(map (fn [x] (pascals-triangle x 4)) (range 5))
(map (fn [x] (pascals-triangle x 5)) (range 6))

; 연습문제 1.13

(def phi (/ (+ 1 (Math/sqrt 5)) 2))

(def psi (/ (- 1 (Math/sqrt 5)) 2))

(defn f [n]
  (/ (Math/pow phi n) (Math/sqrt 5)))

(defn g [n]
  (/ (- (Math/pow phi n) (Math/pow psi n)) (Math/sqrt 5)))

(defn diff [a b]
  (Math/abs (- a b)))

(every? (fn [x] (<= (diff (fib x) (f x)) 0.5)) (range 50))
(every? (fn [x] (<= (diff (fib x) (g x)) 0.001)) (range 50))

; 황금비 (p.50)

(format "%.10f" (- phi 1.6180))
(- (Math/pow phi 2) (+ phi 1))

; 1.2.3 프로세스가 자라나는 정도 / Orders of Growth (p.55)

; 연습문제 1.15

(defn abs [x] (Math/abs x))

(defn cube [x] (* x x x))

(defn p [x] (- (* 3 x) (* 4 (cube x))))

(defn sine [angle]
  (if (not (> (abs angle) 0.1))
    angle
    (p (sine (/ angle 3.0)))))

(sine 12.15)

(apply / (cons 12.15 (repeat 5 3)))
; => p를 5번 부르게 됨.

; 1.2.4 거듭제곱 / Exponentiation (p.57)

(defn expt [b n]
  (if (= n 0)
    1
    (* b (expt b (- n 1)))))

(expt 10 5)

(defn expt-iter [b counter product]
  (if (= counter 0)
    product
    (expt-iter b
               (- counter 1)
               (* b product))))

(defn expt [b n]
  (expt-iter b n 1))

(expt 10 5)

(defn square [n] (* n n))

(defn even? [n]
  (= (mod n 2) 0))

(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (* b (fast-expt b (- n 1)))))

(expt 10 10)
(expt 10 11)

; 연습문제 1.16

(defn fast-expt [b n]
  (defn iter [a b n]
    (cond (= n 0) a
          (even? n) (iter a (square b) (/ n 2))
          :else (iter (* a b) b (- n 1))))
  (iter 1 b n))

(expt 10 10)
(expt 10 11)
(expt 10 12)
(expt 10 13)
(expt 10 14)
(expt 10 15)

; 연습문제 1.17

(* 3 4)
(* 9 25)

(defn * [a b]
  (if (= b 0)
    0
    (+ a (* a (- b 1)))))

(* 3 4)
(* 9 25)

(defn double [n] (* 2 n))

(defn halve [n] (/ 2 n))

(defn * [a b]
  (cond (= b 0) 0
        (even? b) (* (+ a a) (/ b 2))
        :else (+ a (* a (- b 1)))))

(* 3 4)
(* 9 25)

; 연습문제 1.18

(defn * [a b]
  (defn iter [sum a b]
    (cond (= b 0) sum
          (even? b) (iter sum (+ a a) (/ b 2))
          :else (iter (+ sum a) a (- b 1))))
  (iter 0 a b))

(* 3 4)
(* 9 25)

; Clear Namespace...

(ns test)
(remove-ns 'test)
(ns test)

; 연습문제 1.19

(defn fib-iter [a b p q count]
  (cond (= count 0) b
        (even? count) (fib-iter a
                                b
                                (+ (* p p) (* q q))
                                (+ (* 2 p q) (* q q))
                                (/ count 2))
        :else (fib-iter (+ (* b q) (* a q) (* a p))
                        (+ (* b p) (* a q))
                        p
                        q
                        (- count 1))))

(defn fib [n]
  (fib-iter 1 0 0 1 n))

(map fib (range 11))
