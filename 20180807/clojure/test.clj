(ns test)
(remove-ns 'test)
(ns test)

; funcions ---

(defn normalize [s]
  (remove #{\space}
          (clojure.string/lower-case s)))

(defn palindrome-permutation? [s]
  (<= (count
        (filter odd?
                (vals (frequencies (normalize s)))))
      1))

; test ---

(= true (palindrome-permutation? "Tact Coa"))
(= true (palindrome-permutation? "abc ba"))
(= false (palindrome-permutation? "abc"))

; funcions ---

(defn only-one? [a b]
  (defn step [count a b]
    (cond
      (> count 1) false
      (every? nil? [(first a) (first b)]) true
      (= (first a) (first b)) (recur count (rest a) (rest b))
      :else (or (step (+ count 1) a (rest b))
                (step (+ count 1) (rest a) b)
                (step (+ count 1) (rest a) (rest b)))))
  (step 0 a b))

; test ---

(= true (only-one? "pale" "ple"))
(= true (only-one? "pales" "pale"))
(= true (only-one? "pale" "bale"))
(= false (only-one? "pale" "bake"))
