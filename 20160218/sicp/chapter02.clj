(ns test)
(remove-ns 'test)
(ns test)

; 연습문제 2.21

(defn square [x] (* x x))

(defn square-list [items]
  (if (empty? items)
    nil
    (cons (square (first items))
          (square-list (next items)))))

(square-list (list 1 2 3 4))

(defn square-list [items]
  (map square items))

(square-list (list 1 2 3 4))

; 연습문제 2.22

(defn square-list [items]
  (defn iter [things answer]
    (if (empty? things)
      answer
      (iter (next things)
            (cons (square (first things))
                  answer))))
  (iter items nil))

(square-list (list 1 2 3 4))

(defn square-list [items]
  (defn iter [things answer]
    (if (empty? things)
      answer
      (iter (next things)
            (cons answer
                  (square (first things))))))
  (iter items nil))

; (square-list (list 1 2 3 4))
; => java.lang.IllegalArgumentException: Don't know how to create ISeq from: java.lang.Long

(defn square-list [items]
  (defn iter [things answer]
    (if (empty? things)
      answer
      (iter (next things)
            (concat answer
                    [(square (first things))]))))
  (iter items nil))

(square-list (list 1 2 3 4))

(defn square-list [items]
  (defn iter [things answer]
    (if (empty? things)
      answer
      (iter (next things)
            (conj (apply vector answer)
                  (square (first things))))))
  (iter items nil))

(square-list (list 1 2 3 4))

; 연습문제 2.23

(defn for-each [proc items]
  (defn step []
    (proc (first items))
    (for-each proc (next items)))
  (if (empty? items)
    nil
    (step)))

(newline)
(println "----")
(for-each (fn [x] (newline) (print x))
          (list 57 321 88))
