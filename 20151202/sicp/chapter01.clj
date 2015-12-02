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

; 연습문제 1.29

(defn integral [f a b n]
  (def h (/ (- b a) n))
  (defn y [k]
    (f (+ a
          (* k h))))
  (defn g [k]
    (cond (or (= k 0) (= k n)) 1
          (= (mod k 2) 0) 2
          :else 4))
  (defn x [k]
    (* (g k) (y k)))
  (* (/ h 3)
     (sum x 0 inc n)))

(integral cube 0 1 2)
(integral cube 0 1 4)
(integral cube 0 1 6)
(integral cube 0 1 8)
(integral cube 0 1 10)

(integral cube 0 1 100)

(integral cube 0 1 1000)
