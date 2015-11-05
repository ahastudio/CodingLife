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

; ---- 여기까지는 지난 주에 코딩한 내용 ----

; 연습문제 1.21

(smallest-divisor 199)
(smallest-divisor 1999)
(smallest-divisor 19999)

; 연습문제 1.22

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

(reset! result "")
(timed-prime-test 19999)
(apply str @result)

(defn search-for-primes [n]
  (if (timed-prime-test n)
    n
    (search-for-primes (+ n 1))))

(reset! result "")
(search-for-primes 1000)
(search-for-primes 1010)
(search-for-primes 1014)
(apply str @result)

(reset! result "")
(search-for-primes 10000)
(search-for-primes 10008)
(search-for-primes 10010)
(apply str @result)

(reset! result "")
(search-for-primes 100000)
(search-for-primes 100004)
(search-for-primes 100020)
(apply str @result)

(reset! result "")
(search-for-primes 1000000)
(search-for-primes 1000004)
(search-for-primes 1000034)
(apply str @result)
