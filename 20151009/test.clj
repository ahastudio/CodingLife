; 시침과 분침이 직각이 되는 순간은?
; http://codingdojang.com/scode/487

; 문제에 없는 조건을 추가: 분침은 정확히 그 시각의 “분”을 가르키고 있어야 한다.

(ns reload.core)

(defn abs [x]
  (if (< x 0) (- x) x))

(defn right-angle? [h m]
  (let [hour-angle (+ (/ (mod h 12) 12)
                      (/ m 60 12))
        minute-angle (/ m 60)
        angle (abs (- hour-angle minute-angle))]
    (or (= angle (/ 1 4))
        (= angle (/ 3 4)))))

(= true (right-angle? 9 0))
(= true (right-angle? 15 0))
(= true (right-angle? 11 40))

(= false (right-angle? 10 0))
(= false (right-angle? 3 30))

(defn product [a b & seq]
  (if (empty? seq)
    (reduce #(concat %1 (map (partial vector %2) b)) [] a)
    (let [p (apply product (cons b seq))]
      (reduce #(concat %1 (map (partial cons %2) p)) [] a))))

(product [1 2 3] [4 5 6])
(product [1 2] [3 4] [5 6])

(filter #(apply right-angle? %)
        (product (range 24) (range 60)))

; 문제에 없는 조건을 추가했더니 제대로 된 결과가 나오지 않는 것 같다.
