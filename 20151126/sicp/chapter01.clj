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

; ---- 여기까지는 예전에 코딩한 내용 ----

; 연습문제 1.27

(defn fermat-test-all? [n]
  (defn try-it [a]
    (= (expmod a n n) a))
  (defn iter [a]
    (cond (= a 0) true
          (try-it a) (iter (- a 1))
          :else false))
  (iter (- n 1)))

(= true (fermat-test-all? 1))
(= true (fermat-test-all? 2))
(= true (fermat-test-all? 3))
(= false (fermat-test-all? 4))
(= true (fermat-test-all? 5))
(= false (fermat-test-all? 6))
(= false (fermat-test-all? 6))
(= true (fermat-test-all? 13))
(= true (fermat-test-all? 23))

(every? #(= (fermat-test-all? %) (prime? %))
        (range 1 100))

(every? #(and (= true (fermat-test-all? %))
              (= false (prime? %)))
        [561 1105 1729 2465 2821 6601])

; 연습문제 1.28

(defn miller-test-all? [n]
  (defn try-it [a]
    (= (expmod a (- n 1) n) 1))
  (defn iter [a]
    (cond (= a 0) true
          (try-it a) (iter (- a 1))
          :else false))
  (iter (- n 1)))

(every? #(= (miller-test-all? %) (prime? %))
        (range 1 100))

(every? #(and (= false (miller-test-all? %))
              (= false (prime? %)))
        [561 1105 1729 2465 2821 6601])

(defn miller-test [n]
  (defn try-it [a]
    (= (expmod a (- n 1) n) 1))
  (try-it (+ 1 (rand-int (- n 1)))))

(defn fast-prime? [n times]
  (cond (= times 0) true
        (miller-test n) (fast-prime? n (- times 1))
        :else false))

(every? #(= (fast-prime? % 10) (prime? %))
        (range 1 1000))
