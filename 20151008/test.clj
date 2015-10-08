; Duplicate Numbers
; http://codingdojang.com/scode/488

(ns reload.core)

(defn digits [n] (map #(Integer. %) (re-seq #"\d" n)))

(defn duplicate-numbers? [n]
  (= (sort (digits n)) (range 10)))

(= true (duplicate-numbers? "0123456789"))
(= true (duplicate-numbers? "1234567890"))

(= false (duplicate-numbers? "123456789"))
(= false (duplicate-numbers? "01234567890"))

(= [1 2 3] (digits "123"))
(= [1 3 2] (digits "132"))

(= [true false false true false]
   (map duplicate-numbers? ["0123456789" "01234" "01234567890" "6789012345" "012322456789"]))
