(ns test)
(remove-ns 'test)
(ns test)

; SICP 스터디에서 분수와 고정소수점 등에 대해서 이야기함.

; 분수

(* (float (/ 1 3)) 3)

(* (/ 1 3) 3)

; 고정소수점

(def pi 3.14)
(def r 0.1)
(* pi (* r r))

(def pi 3.14M)
(def r 0.1M)
(* pi (* r r))

; 1.2.5 최대 공약수 / Greatest Common Divisors (p.62)

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (mod a b))))

; 연습문제 1.20

(def counter (atom 0))

(defn remainder [a b]
  (swap! counter inc)
  (mod a b))

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (remainder a b))))

(reset! counter 0)
(gcd 206 40)
counter

(defn gcd [a b]
  (if (= (b) 0)
    a
    (gcd (b) #(-> (remainder a (b))))))

(reset! counter 0)
(gcd 206 #(-> 40))
counter

; https://clojuredocs.org/clojure.core/defmacro

(defmacro gcd [a b]
  `(if (= ~b 0)
     ~a
     (eval '(gcd ~b (remainder ~a ~b)))))

(reset! counter 0)
(gcd 206 40)
counter

; [번외편] 연습문제 1.6에서 나온 new-if를 Macro로 구현할 수 있을까?

(defn new-if [condition then else]
  (cond condition then
        :else else))

(new-if (> 2 1) "T" "F")
(new-if (< 2 1) "T" "F")

(defn iter [a b counter]
  (new-if (> a b)
          counter
          (iter (+ a 1) b (+ counter 1))))

; (iter 3 10 0)
; java.lang.StackOverflowError: null
; -> 무한반복!

(defmacro new-if [condition then else]
  `(cond ~condition ~then
         :else ~else))

(new-if (> 2 1) "T" "F")
(new-if (< 2 1) "T" "F")

; Macro를 쓰기 때문에, 여기서 iter를 다시 정의해야 함.
(defn iter [a b counter]
  (new-if (> a b)
          counter
          (iter (+ a 1) b (+ counter 1))))

(iter 3 10 0)
