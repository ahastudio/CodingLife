; 카드 역배치
; http://codingdojang.com/scode/486

(ns test)
(remove-ns 'test)
(ns test)

(defn flip-cards
  ([cards] cards)
  ([cards a b & seq]
   (let [start (- a 1)
         size (- (+ b 1) a)]
     (apply flip-cards
            (concat (take start cards)
                    (reverse (take size (drop start cards)))
                    (drop b cards))
            seq))))

(= '(1 2 6 5 4 3 7 8 9 10)
   (flip-cards '(1 2 3 4 5 6 7 8 9 10)
               3 6))

(= '(1 2 6 5 10 9 8 7 3 4)
   (flip-cards '(1 2 3 4 5 6 7 8 9 10)
               3 6 5 10))

(= '(1 10 5 6 2 9 8 7 3 4)
   (flip-cards '(1 2 3 4 5 6 7 8 9 10)
               3 6 5 10 2 5))

(= '(1 10 5 6 2 3 7 8 9 4)
   (flip-cards '(1 2 3 4 5 6 7 8 9 10)
               3 6 5 10 2 5 6 9))

(= '(1 2 3 4 5 6 7 8 9 10)
   (flip-cards '(1 2 3 4 5 6 7 8 9 10)
               1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10 10))
