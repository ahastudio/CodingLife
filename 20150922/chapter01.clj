(ns reload.core)

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
