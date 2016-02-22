(ns htdp)
(remove-ns 'htdp)
(ns htdp)

; 6.1 구조체

(defstruct posn :x :y)

(defn make-posn [x y]
  (struct posn x y))

(make-posn 3 4)

(defn posn-x [a-posn]
  (a-posn :x))

(defn posn-y [a-posn]
  (a-posn :y))

(posn-x (make-posn 7 0))
(posn-y (make-posn 7 0))

(defn sqr [x]
  (* x x))

(defn distance-to-0 [a-posn]
  (Math/sqrt
   (+ (sqr (posn-x a-posn))
      (sqr (posn-y a-posn)))))

(distance-to-0 (make-posn 0 5))
(distance-to-0 (make-posn 7 0))

; 연습문제 6.1.1

(distance-to-0 (make-posn 3 4))
(distance-to-0 (make-posn (* 2 3) (* 2 4)))
(distance-to-0 (make-posn 12 (- 6 1)))

; 6.3 구조체 정의

(defmacro define-struct [struct-name & fields]
  (eval `(defstruct ~struct-name ~@fields))
  (eval `(intern *ns*
                 (symbol (str "make-" ~(name struct-name)))
                 (partial struct ~struct-name))))

(define-struct entry :name :zip :phone)

(keys (ns-publics *ns*))

(make-entry 'PeterLee 15270 "606-7771")

(def phonebook
  (make-entry 'PeterLee 15270 "606-7771"))

; (entry-name phonebook)
; => 이거까지 만들기 귀찮음

(phonebook :name)
(phonebook :zip)
(phonebook :phone)

; 연습문제 6.3.2

(define-struct movie :title :producer)

((make-movie 'ThePhantomMenace 'Lucas) :title)
((make-movie 'TheEmpireStrikesBack 'Lucas) :producer)

; 연습문제 6.3.3

(define-struct aircraft :name :acceleration :max-velocity :range)

(defn within-range [aircraft range]
  (>= (aircraft :range) range))

(def fighter (make-aircraft 'fighter 10 200 37))

(= true
   (within-range fighter 30))
(= false
   (within-range fighter 40))

(defn reduce-range [aircraft]
  (make-aircraft (aircraft :name)
                 (aircraft :acceleration)
                 (aircraft :max-velocity)
                 (* (aircraft :range) 0.8)))

(= (make-aircraft 'fighter 10 200 (* 37 0.8))
   (reduce-range fighter))
