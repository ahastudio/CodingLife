(ns test)
(remove-ns 'test)
(ns test)

; 1.2.6 연습: 소수 찾기 / Example: Testing for Primality

; 약수 찾기

(defn square [n] (* n n))

(= (square 2) 4)
(= (square 3) 9)

(defn divides? [a b]
  (= (mod b a) 0))

(= (divides? 1 3) true)
(= (divides? 2 3) false)
(= (divides? 3 3) true)

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(smallest-divisor 1)
(smallest-divisor 2)
(smallest-divisor 3)
(smallest-divisor 4)
(smallest-divisor 5)
(smallest-divisor 6)
(smallest-divisor 7)
(smallest-divisor 8)
(smallest-divisor 9)
(smallest-divisor 10)
(smallest-divisor 11)
(smallest-divisor 12)

(defn prime? [n]
  (= n (smallest-divisor n)))

(filter prime? (range 1 100))

; 페르마 검사

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (square (expmod base (/ exp 2) m))
                         m)
        :else (mod (* base (expmod base (- exp 1) m))
                   m)))

(expmod 1 1 1)
(expmod 1 1 2)

(map (fn [x] [x (expmod 10 1 x)]) (range 1 11))
(map (fn [x] [x (expmod 3 2 x)]) (range 1 11))
(map (fn [x] [x (expmod 3 3 x)]) (range 1 11))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (- times 1))
        :else false))

(fast-prime? 1 10)
(fast-prime? 2 10)
(fast-prime? 3 10)
(fast-prime? 4 10)

(filter #(fast-prime? % 10) (range 1 100))
