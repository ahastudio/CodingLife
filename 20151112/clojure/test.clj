(ns test)
(remove-ns 'test)
(ns test)

; Quick Sort

(defn qsort
  ([] [])
  ([pivot & v]
   (concat (apply qsort (filter (partial >= pivot) v))
           [pivot]
           (apply qsort (filter (partial < pivot) v)))))

(qsort 1 2 3 4 5 6 7 8 9)
(qsort 1 2 3 5 6 7 8 9 4)
(qsort 9 8 7 6 5 4 3 2 1)
(qsort 3 2 1 9 8 7 6 5 4)
(qsort 1 2 1 9 2 1 3 3 9)

; Merge Sort

(defn msort [v]
  (defn step [result left right]
    (cond (empty? left) (concat result right)
          (empty? right) (concat result left)
          :else (let [a (first left)
                      b (first right)]
                  (if (< a b)
                    (step (conj result a) (drop 1 left) right)
                    (step (conj result b) left (drop 1 right))))))
  (let [length (count v)]
    (if (<= length 1)
      v
      (let [half (quot length 2)
            left (msort (take half v))
            right (msort (drop half v))]
        (step [] left right)))))

(msort [1 2 3 4 5 6 7 8 9])
(msort [1 2 3 5 6 7 8 9 4])
(msort [9 8 7 6 5 4 3 2 1])
(msort [3 2 1 9 8 7 6 5 4])
(msort [1 2 1 9 2 1 3 3 9])
