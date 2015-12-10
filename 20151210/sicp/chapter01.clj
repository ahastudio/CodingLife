(ns test)
(remove-ns 'test)
(ns test)

; 1.3 차수 높은 프로시저로 요약하는 방식

; 1.3.1 프로시저를 인자로 받는 프로시저

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn cube [n] (* n n n))

(defn inc [n] (+ n 1))

(defn sum-cubes [a b]
  (sum cube a inc b))

(sum-cubes 1 10)

(defn integral [f a b dx]
  (defn add-dx [x] (+ x dx))
  (* (sum f (+ a (/ dx 2.0)) add-dx b)
     dx))

(integral cube 0 1 0.01)

(integral cube 0 1 0.001)

(sum (fn [x] x) 0 inc 100)

; 연습문제 1.30

(defn sum [term a next b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next a) (+ result (term a)))))
  (iter a 0))

(sum (fn [x] x) 0 inc 100)

(sum-cubes 1 10)

(integral cube 0 1 0.01)

(integral cube 0 1 0.001)

; 연습문제 1.31

(defn product [term a next b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next a) (* result (term a)))))
  (iter a 1))

(= (product (fn [x] x) 1 inc 10)
   (apply * (range 1 11)))

(defn f [x]
  (/ (+ 2 (* 2 (int (/ (+ x 1) 2))))
     (+ 3 (* 2 (int (/ x 2))))))

(= (/ 2 3) (f 0))
(= (/ 4 3) (f 1))
(= (/ 4 5) (f 2))
(= (/ 6 5) (f 3))

(= (/ (* 2 4) (* 3 3))
   (product f 0 inc 1))

(* (float (product f 0 inc 1000))
   4)

; b.

(defn product [term a next b]
  (if (> a b)
    1
    (* (term a)
       (product term (next a) next b))))

(= (product (fn [x] x) 1 inc 10)
   (apply * (range 1 11)))

(* (float (product f 0 inc 1000))
   4)
