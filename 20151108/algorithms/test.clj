(ns test)
(remove-ns 'test)
(ns test)

; 1장 연습 문제 16번

(def count-know (atom 0))

(defn know [i j]
  (swap! count-know #(+ 1 %))
  (let [table [[4]
               [0 4]
               [1 4]
               [0 1 2 4]
               []
               [0 1 4]
               [2 4]]]
    (some #(= j %) (table i))))

(know 0 4)
(know 4 0)

; Theta(n^2)

(defn celebrity [n f]
  (defn step-i [a i]
    (defn step-j [a j]
      (if (= j n)
        (conj a i)
        (if (or (= i j)
                (not (know i j)))
          (step-j a (+ j 1))
          a)))
    (if (= i n)
      a
      (step-i (step-j a 0) (+ i 1))))
  (first (step-i [] 0)))

(reset! count-know 0)
(celebrity 3 know)
@count-know

(reset! count-know 0)
(celebrity 7 know)
@count-know

; Theta(n)

(defn celebrity [n f]
  (defn step [people]
    (if (<= (count people) 1)
      (first people)
      (let [i (first people)
            j (second people)]
        (if (know i j)
          (step (drop 1 people))
          (step (cons i (drop 2 people)))))))
  (step (range n)))

(reset! count-know 0)
(celebrity 3 know)
@count-know

(reset! count-know 0)
(celebrity 7 know)
@count-know
