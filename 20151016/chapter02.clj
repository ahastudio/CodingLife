(ns htdp)
(remove-ns 'htdp)
(ns htdp)

; 2장 - 수, 표현, 간단한 프로그램


; 연습문제 2.1.1

(Math/pow 2 2)

(Math/sin 0)
(Math/sin (/ Math/PI 2))
(Math/sin (/ Math/PI 4))

(Math/max 2 3)

; 연습문제 2.1.2

(Math/sqrt 4)
(Math/sqrt 2)
(Math/sqrt -1)

(Math/tan 0)
(Math/tan (* Math/PI 0.5))
(Math/tan (* Math/PI 0.49999))
(Math/tan (* Math/PI 0.50001))

; 2.2 변수와 프로그램

(defn area-of-disk [r]
  (* 3.14M (* r r)))

; 연습문제 2.2.1

(defn Fahrenheit->Celsius [fahrenheit]
  (/ (- fahrenheit 32) 1.8))

(Fahrenheit->Celsius 1)

; 연습문제 2.2.2

(defn dollar->euro [dollar]
  (* 0.88046383 dollar))

(dollar->euro 100)

; 연습문제 2.2.3

(defn triangle [base height]
  (/ (* base height) 2))

(triangle 10 10)

; 연습문제 2.2.4

(defn convert3 [a b c]
  (+ a (* b 10) (* c 100)))

(convert3 1 2 3)

; 연습문제 2.2.5

(defn f [n]
  (+ (Math/pow n 2) 10))

(f 2)
(f 9)

(defn f[n]
  (+ (* (/ 1 2) (Math/pow n 2)) 20))

(f 2)
(f 9)

(defn f [n]
  (- 2 (/ 1 n)))

(f 2)
(f 9)

; 연습문제 2.3.1

(defn tax [income]
  (* 0.15 income))

(tax 1000)

(defn wage [h]
  (* 12 h))

(defn netpay [h]
  (* (wage h) (- 1 0.15)))

(wage 1000)
(netpay 1000)

; 연습문제 2.3.2

(defn sum-coins [penny nickel dime quarter]
  (+ (* penny 1)
     (* nickel 5)
     (* dime 10)
     (* quarter 25)))

(sum-coins 3 2 1 4)

; 연습문제 2.3.3

(defn total-profit [n]
  (- (* n 5)
     (+ 20 (* n 0.50))))

(total-profit 0)
(total-profit 1)
(total-profit 4)
(total-profit 5)
(total-profit 10)

; 연습문제 2.4.1

; (+ (10) 20)
; -> java.lang.ClassCastException: java.lang.Long cannot be cast to clojure.lang.IFn

; (10 + 20)
; -> java.lang.ClassCastException: java.lang.Long cannot be cast to clojure.lang.IFn

; (+ +)
; -> java.lang.ClassCastException: Cannot cast clojure.core$_PLUS_ to java.lang.Number

; 연습문제 2.4.2

(defn f [x]
  (+ x 10))

(f 10)
; Failed trying to require htdp with: java.lang.NullPointerException: null

(defn g [x]
  + x 10)

(g 10)
; -> Clojure에선 에러가 나지 않는다 ㅎㄷㄷ

(defn h [x]
  (+ x 10))
; java.lang.IllegalArgumentException: Parameter declaration x should be a vector

(h 10)

; 연습문제 2.4.3

; (+ 5 (/ 1 0))
; java.lang.ArithmeticException: Divide by zero

; (Math/sin 10 20)
; clojure.lang.Compiler$CompilerException: java.lang.IllegalArgumentException:
; No matching method: sin, compiling:

; (somef 10)
; clojure.lang.Compiler$CompilerException: java.lang.RuntimeException:
; Unable to resolve symbol: somef in this context, compiling:

; 2.5 프로그램 디자인

; (1) 프로그램의 목적 이해

; (2) 프로그램 예

; (3) 구현부

(defn area-of-ring [outer inner]
  (- (area-of-disk outer)
     (area-of-disk inner)))

; (4) 테스트

(area-of-ring 5 3)
