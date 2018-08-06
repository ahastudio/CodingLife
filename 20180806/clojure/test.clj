(ns test)
(remove-ns 'test)
(ns test)

; funcions ---

(defn permutation? [a b]
  (= (sort a)
     (sort b)))

; test ---

(= true (permutation? "abc" "cba"))
(= false (permutation? "abc" "aaa"))

; funcions ---

(def ascii-buffer
  (vec (replicate 256 0)))

(defn ascii-counts [s]
  (loop [[head & tail] s
         counts ascii-buffer]
    (if (= head nil)
      counts
      (let [key (int head)
            count (+ 1 (counts key))]
        (recur tail
               (assoc counts key count))))))

(defn permutation? [a b]
  (every? (fn [[a b]] (= a b))
          (map vector
               (ascii-counts a)
               (ascii-counts b))))

; test ---

(= true (permutation? "abc" "cba"))
(= false (permutation? "abc" "aaa"))

; funcions ---

(defn urlize [s]
  (clojure.string/replace s #"\s" "%20"))

; test ---

(urlize "a b")

(= "a%20b"
   (urlize "a b"))

; funcions ---

(defn urlize-unit [x]
  (if (= x \space)
    "%20"
    (str x)))

(defn urlize [s]
  (clojure.string/join
    (map urlize-unit s)))

; test ---

(urlize "a b")

(= "a%20b"
   (urlize "a b"))
