(ns test)
(remove-ns 'test)
(ns test)

; 4장 - 조건문과 함수

; 연습문제 4.3.1

(def n 12)

(cond
 (< n 10) 20
 (> n 20) 0
 :else 1)

(cond
 (< n 10) 20
 (and (> n 20) (<= n 30)) '여기가-빠졌음
 :else 1)

(cond (< n 10) 20
      '여기가-빠졌음 (* 10 n)
      :else 555)

(def n nil)

; 연습문제 4.3.2

(defn test [n]
  (cond
   (<= n 1000) 0.040
   (<= n 5000) 0.045
   (<= n 10000) 0.055
   (> n 10000) 0.060))

(test 500)
(test 2800)
(test 15000)

; 연습문제 4.3.3

(defn test [n]
  (cond
   (<= n 1000) (* 0.040 1000)
   (<= n 5000) (+ (* 1000 0.040)
                  (* (- n 1000) 0.045))
   :else (+ (* 1000 0.040)
            (* 4000 0.045)
            (* (- n 10000) 0.055))))

(test 500)
(test 2800)
(test 15000)

; 연습문제 4.4.1

(defn interest-rate [amount]
  (cond (<= amount 1000) 0.04
        (<= amount 5000) 0.045
        :else 0.05))

(defn interest [amount]
  (* amount (interest-rate amount)))

(interest 500)
(interest 1000)
(interest 3000)
(interest 5000)
(interest 10000)

; 연습문제 4.4.2

(defn tax [n]
  (defn rate [n]
    (cond (<= n 240) 0
          (<= n 480) 0.15
          :else 0.28))
  (* n (rate n)))

(defn netpay [h]
  (let [pay (* h 12)]
    (- pay (tax pay))))

(netpay 10)
(netpay 20)
(netpay 30)
(netpay 40)
(netpay 50)

; 연습문제 4.4.3

(defn pay-back [n]
  (cond (<= n 500) (* n 0.0025)
        (<= n 1500) (+ (* (- n 500) 0.0050)
                       (pay-back 500))
        (<= n 2500) (+ (* (- n 1500) 0.0075)
                       (pay-back 1500))
        :else (+ (* (- n 2500) 0.0075)
                       (pay-back 2500))))

(pay-back 400)
(pay-back 1400)
(pay-back 2000)
(pay-back 2600)

; 연습문제 4.4.4

(defn how-many [a b c]
  (let [result (- (* b b)
                  (* 4 a c))]
    (cond (> result 0) 2
          (== result 0) 1
          :else 0)))

(how-many 1 0 -1)
(how-many 2 4 2)
