(ns test)
(remove-ns 'test)
(ns test)

; 연습문제 2.29 (p.144)

(defn make-mobile [left right]
  (list left right))

(defn make-branch [length structure]
  (list length structure))

; a.

(defn left-branch [mobile]
  (first mobile))

(defn right-branch [mobile]
  (second mobile))

(left-branch (make-mobile (make-branch 1 2)
                          (make-branch 2 1)))
(right-branch (make-mobile (make-branch 1 2)
                           (make-branch 2 1)))

(defn branch-length [branch]
  (first branch))

(defn branch-structure [branch]
  (second branch))

(branch-length (make-branch 2 10))
(branch-structure (make-branch 2 10))

; b.

(def total-weight) ; declare

(defn branch-weight [branch]
  (if (nil? branch)
    0
    (let [structure (branch-structure branch)]
      (if (number? structure)
        structure
        (total-weight structure)))))

(defn total-weight [mobile]
  (+ (branch-weight (left-branch mobile))
     (branch-weight (right-branch mobile))))

(def mobile-part
  (make-mobile (make-branch 1 2)
               (make-branch 2 1)))

(= 4
  (total-weight (make-mobile (make-branch 3 1)
                             (make-branch 1 mobile-part))))

; c.

(defn branch-toque [branch]
  (* (branch-length branch)
     (branch-weight branch)))

(defn mobile-balanced? [mobile]
  (defn branch-balanced? [branch]
    (if (nil? branch)
      true
      (let [structure (branch-structure branch)]
        (if (number? structure)
          true
          (mobile-balanced? structure)))))
  (let [left (left-branch mobile)
        right (right-branch mobile)]
    (and (= (branch-toque left)
            (branch-toque right))
         (branch-balanced? left)
         (branch-balanced? right))))

(= true
   (mobile-balanced? (make-mobile (make-branch 3 1)
                                  (make-branch 1 mobile-part))))

(= false
   (mobile-balanced? (make-mobile (make-branch 3 2)
                                  (make-branch 1 mobile-part))))

; d.
; Clojure에선 (cons 1 2)가 불가능하다. (cons 1 [2]) 같은 형태만 가능.
; 여기선 hash-map을 써서 괜히 left-branch와 branch-length도 고치게 했다.
; (원래는 right-branch와 branch-structure만 고치는 게 문제의 출제 의도)

(defn make-mobile [left right]
  {:left left :right right})

(defn make-branch [length structure]
  {:length length :structure structure})

(defn left-branch [mobile]
  (mobile :left))

(defn right-branch [mobile]
  (mobile :right))

(left-branch (make-mobile (make-branch 1 2)
                          (make-branch 2 1)))
(right-branch (make-mobile (make-branch 1 2)
                           (make-branch 2 1)))

(defn branch-length [branch]
  (branch :length))

(defn branch-structure [branch]
  (branch :structure))

(branch-length (make-branch 2 10))
(branch-structure (make-branch 2 10))

(def mobile-part
  (make-mobile (make-branch 1 2)
               (make-branch 2 1)))

(= 4
  (total-weight (make-mobile (make-branch 3 1)
                             (make-branch 1 mobile-part))))

(= true
   (mobile-balanced? (make-mobile (make-branch 3 1)
                                  (make-branch 1 mobile-part))))
