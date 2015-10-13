(ns test)
(remove-ns 'test)
(ns test)

; 1.1.1 (p.8)

(+ 137 349)
(- 1000 334)
(* 5 99)
(/ 10 5)
(+ 2.7 10)

(+ 21 35 12 7)
(* 25 4 12)

(+ (* 3 5) (- 10 6))

(+ (* 3 (+ (* 2 4) (+ 3 5))) (+ (- 10 7) 6))

(+ (* 3
      (+ (* 2 4)
         (+ 3 5)))
   (+ (- 10 7)
      6))

; 1.1.2

; https://clojuredocs.org/clojure.core/def

(def size 2)

size

(* 5 size)

(def pi 3.14159)

(def radius 10)

(* pi (* radius radius))

(def circumference (* 2 pi radius))

circumference

; 1.1.3


(* (+ 2 (* 4 6))
   (+ 3 5 7))

; 1.1.4

; https://clojuredocs.org/clojure.core/defn

(defn square [x] (* x x))

(square 21)
(square (+ 2 5))
(square (square 3))

(def x 3)
(def y 4)
(+ (square x) (square y))

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(sum-of-squares 3 4)

(defn f [a]
  (sum-of-squares (+ a 1) (* a 2)))

(f 5)

; 1.1.5

(f 5)
(def a 5)
(sum-of-squares (+ a 1) (* a 2))
(sum-of-squares (+ 5 1) (* 5 2))
(sum-of-squares 6 10)
(+ (square 6) (square 10))
(+ (* 6 6) (* 10 10))
(+ 36 100)

; 1.1.6

; https://clojuredocs.org/clojure.core/cond

(defn abs [x]
  (cond (> x 0) x
        (= x 0) 0
        (< x 0) (- x)))

(abs 10)
(abs 0)
(abs -10)

(defn abs [x]
  (cond (< x 0) (- x)
        :else x))

(abs 10)
(abs 0)
(abs -10)

; https://clojuredocs.org/clojure.core/if

(defn abs [x]
  (if (< x 0) (- x) x))

(abs 10)
(abs 0)
(abs -10)

(and (> x 5) (< x 10))

(defn >= [x y]
  (or (> x y) (= x y)))

(>= 11 10)
(>= 10 10)
(>= 9 10)

(defn >= [x y]
  (not (< x y)))

(>= 11 10)
(>= 10 10)
(>= 9 10)

; 연습문제 1.1 (p.26)

(+ 5 3 4)

(- 9 1)

(/ 6 2)

(+ (* 2 4) (- 4 6))

(def a 3)

(def b (+ a 1))

(+ a b (* a b))

(= a b)

(if (and (> b a) (< b (* a b)))
  b
  a)

(cond (= a 4) 6
      (= b 4) (+ 6 7 a)
      :else 25)

(+ 2 (if (> b a) b a))

(* (cond (> a b) a
         (< a b) b
         :else -1)
   (+ a 1))

; 연습문제 1.2

(/ (+ 5
      4
      (- 2
         (- 3
            (+ 6 (/ 4 5)))))
   (* 3
      (- 6 2)
      (- 2 7)))

; 연습문제 1.3

(defn sum-of-two-largest-numbers-squares [x y z]
  (cond (and (>= x z) (>= y z)) (sum-of-squares x y)
        (and (>= x y) (>= z y)) (sum-of-squares x z)
        (and (>= y x) (>= z x)) (sum-of-squares y z)))

(sum-of-two-largest-numbers-squares 1 2 3)
(sum-of-two-largest-numbers-squares 2 3 1)
(sum-of-two-largest-numbers-squares 3 1 2)

; 연습문제 1.4

(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

(a-plus-abs-b 2 5)
(a-plus-abs-b 2 0)
(a-plus-abs-b 2 -5)

; 연습문제 1.5

(defn p [] (p))

(defn test [x y]
  (if (= x 0) 0 y))

; (test 0 (p))
; => 인자 먼저 계산을 하기 때문에 무한루프에 빠짐.

; 1.1.7 (p.29)

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
               x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(sqrt 9)

(sqrt (+ 100 37))

(sqrt (+ (sqrt 2) (sqrt 3)))

(square (sqrt 1000))

; 연습문제 1.6

(defn new-if [predicate then-clause else-clause]
  (cond predicate then-clause
        :else else-clause))

(new-if (= 2 3) 0 5)

(new-if (= 1 1) 0 5)

(defn wrong-sqrt-iter [guess x]
  (new-if (good-enough? guess x)
          guess
          (wrong-sqrt-iter (improve guess x)
                           x)))

; (wrong-sqrt-iter 1.0 9)
; => new-if가 프로시저라 인자 먼저 계산을 하기 때문에 else-clause 무한루프에 빠짐.

(defn new-good-enough? [guess x old]
  (< (abs (- 1 (/ guess old)))
     0.001))

(defn new-sqrt-iter [guess x old]
  (if (new-good-enough? guess x old)
    guess
    (new-sqrt-iter (improve guess x)
                   x
                   guess)))

(def small (square 0.00000001))
(format "%.10f" (sqrt-iter 1.0 small))
(format "%.10f" (new-sqrt-iter 1.0 small small))

(def large (+ (square 9999999) 9))
; (sqrt-iter 1.0 large)
; => StackOverflowError
(new-sqrt-iter 1.0 large large)

; 연습문제 1.8

(defn cube-root-improve [guess x]
  (/ (+ (/ x
           (square guess))
        (* 2 guess))
     3))

(defn cube-root-iter [guess x old]
  (if (new-good-enough? guess x old)
    guess
    (cube-root-iter (cube-root-improve guess x)
                     x
                     guess)))

(defn cube-root [x]
  (cube-root-iter 1.0 x x))

(defn cube [x]
  (* x x x))

(cube-root (cube 3))
(cube-root (cube -1234567))
(format "%.10f" (cube-root (cube 0.00001)))

; 1.1.8 (p.34)

(defn square [x] (* x x))

; (defn square [x] (exp (double (log x))))

(double 10)
; => 10.0

(defn double [x] (+ x x))

(double 10)
; => 20

; 블록 구조

(defn sqrt [x]
  (defn good-enough? [guess x]
    (< (abs (- (square guess) x)) 0.001))
  (defn improve [guess x]
    (average guess (/ x guess)))
  (defn sqrt-iter [guess x]
    (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x) x)))
  (sqrt-iter 1.0 x))

(sqrt 9)

; lexical scoping 규칙 활용

(defn sqrt [x]
  (defn good-enough? [guess]
    (< (abs (- (square guess) x)) 0.001))
  (defn improve [guess]
    (average guess (/ x guess)))
  (defn sqrt-iter [guess]
    (if (good-enough? guess)
      guess
      (sqrt-iter (improve guess))))
  (sqrt-iter 1.0))

(sqrt 9)
