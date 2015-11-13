(ns test)
(remove-ns 'test)
(ns test)

; Quick Sort

(defn qsort
  ([] [])
  ([pivot & data]
   (concat (apply qsort (filter (partial >= pivot) data))
           [pivot]
           (apply qsort (filter (partial < pivot) data)))))

(qsort 1 2 3 4 5 6 7 8 9)
(qsort 1 2 3 5 6 7 8 9 4)
(qsort 9 8 7 6 5 4 3 2 1)
(qsort 3 2 1 9 8 7 6 5 4)
(qsort 1 2 1 9 2 1 3 3 9)
