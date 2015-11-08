(ns test)
(remove-ns 'test)
(ns test)

; 1장 연습 문제 2번

(defn sum [n s]
  (if (= n 1)
    (first s)
    (let [half (quot n 2)]
      (+ (sum half (take half s))
         (sum (+ half (mod n 2))(drop half s))))))

(sum 1 [1])
(sum 2 [1 2])
(sum 3 [1 2 3])
(sum 4 [1 2 3 4])
(sum 5 [1 2 3 4 5])
(sum 10 (range 1 11))
(sum 100 (range 1 101))
