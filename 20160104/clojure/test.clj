(ns test)
(remove-ns 'test)
(ns test)

(defn max-cost-effectiveness [parts]
  (let [parts (sort > parts)]
    (defn cost-effectiveness [parts]
      (/ (apply + 150 parts)
         (+ 10 (* 3 (count parts)))))
    (apply max (map #(cost-effectiveness (take % parts))
                    (range 0 (+ 1 (count parts)))))))

(= 17
   (int (max-cost-effectiveness [30 70 15 40 65])))
