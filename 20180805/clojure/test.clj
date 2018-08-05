(ns test)
(remove-ns 'test)
(ns test)

; funcions ---

(defn duplicated? [s]
  (not (= (count s)
          (count (set s)))))

; test ---

(= false (duplicated? "abc"))
(= true (duplicated? "abca"))

; funcions ---

(def ascii-buffer
  (vec (replicate 256 false)))

(defn use [used? key]
  (assoc used? key true))

(defn duplicated? [s]
  (loop [[head & tail] s
         used? ascii-buffer]
    (if (= head nil)
      false
      (let [key (int head)]
        (if (used? key)
          true
          (recur tail (use used? key)))))))

; test ---

(= false (duplicated? "abc"))
(= true (duplicated? "abca"))
