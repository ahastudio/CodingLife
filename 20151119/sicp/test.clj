(ns test)
(remove-ns 'test)
(ns test)

; 1.2.6 연습: 소수 찾기 / Example: Testing for Primality

(defn square [n] (* n n))

(defn divides? [a b]
  (= (mod b a) 0))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (+ test-divisor 1))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(def result (atom "RESULT"))

(defn newline []
  (swap! result #(str % \newline)))

(defn display [x]
  (swap! result #(str % x)))

(defn runtime []
  (System/nanoTime))

(defn report-prime [elapsed-time]
  (display " *** ")
  (display elapsed-time))

(defn start-prime-test [n start-time]
  (if (prime? n)
    (report-prime (- (runtime) start-time))))

(defn timed-prime-test [n]
  (newline)
  (display n)
  (start-prime-test n (runtime)))

(defn search-for-primes [n]
  (if (timed-prime-test n)
    n
    (search-for-primes (+ n 1))))

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (square (expmod base (/ exp 2) m))
                         m)
        :else (mod (* base (expmod base (- exp 1) m))
                   m)))

(defn fermat-test [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (fermat-test n) (fast-prime? n (- times 1))
        :else false))

(reset! result "")
(search-for-primes 1000000)
(search-for-primes 1000004)
(search-for-primes 1000034)
(apply str @result)

; ---- 여기까지는 예전에 코딩한 내용 ----

; 연습문제 1.23

(defn next [n]
  (if (= 0 (mod n 2))
    (+ n 1)
    (+ n 2)))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor) n) n
        (divides? test-divisor n) test-divisor
        :else (find-divisor n (next test-divisor))))

(= true (prime? 1))
(= true (prime? 2))
(= true (prime? 3))
(= false (prime? 4))
(= true (prime? 5))
(= false (prime? 6))
(= true (prime? 7))
(= false (prime? 8))
(= false (prime? 9))
(= false (prime? 10))
(= true (prime? 11))
(= false (prime? 12))
(= true (prime? 13))
(= false (prime? 14))
(= false (prime? 15))
(= false (prime? 16))
(= true (prime? 17))
(= false (prime? 18))

(reset! result "")
(search-for-primes 1000000)
(search-for-primes 1000004)
(search-for-primes 1000034)
(apply str @result)

; 연습문제 1.24

(defn start-prime-test [n start-time]
  (if (fast-prime? n 5)
    (report-prime (- (runtime) start-time))))

(reset! result "")
(search-for-primes 1000000)
(search-for-primes 1000004)
(search-for-primes 1000034)
(apply str @result)

; 연습문제 1.25

(defn start-prime-test [n start-time]
  (if (prime? n)
    (report-prime (- (runtime) start-time))))

(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (square (fast-expt b (/ n 2)))
        :else (* b (fast-expt b (- n 1)))))

(defn expmod [base exp m]
  (mod (fast-expt base exp) m))

(reset! result "")
(search-for-primes 1000000)
(search-for-primes 1000004)
(search-for-primes 1000034)
(apply str @result)

; 연습문제 1.26

(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (* (expmod base (/ exp 2) m)
                            (expmod base (/ exp 2) m))
                         m)
        :else (mod (* base (expmod base (- exp 1) m))
                   m)))

(defn start-prime-test [n start-time]
  (if (fast-prime? n 5)
    (report-prime (- (runtime) start-time))))

(reset! result "")
(search-for-primes 1000000)
(search-for-primes 1000004)
(search-for-primes 1000034)
(apply str @result)
