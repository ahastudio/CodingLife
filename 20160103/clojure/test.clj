(ns test)
(remove-ns 'test)
(ns test)

(defn max-index [papers right?]
  (apply max
         (filter right?
                 (range 0 (+ 1 (count papers))))))

(defn h-index [papers]
  (defn right-h? [h]
    (= (count (filter #(>= % h) papers)) h))
  (max-index papers right-h?))

(defn g-index [papers]
  (let [papers (sort > papers)]
    (defn right-g? [g]
      (>= (apply + (take g papers))
          (* g g)))
    (max-index papers right-g?)))

(= 4
   (h-index [0 15 4 0 7 10 0]))

(= 6
   (g-index [0 15 4 0 7 10 0]))
