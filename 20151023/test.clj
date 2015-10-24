(ns test)
(remove-ns 'test)
(ns test)

; 연습문제 1.6에서 나온 new-if를 Macro로 구현할 수 있을까?

(defn new-if [condition then else]
  (cond condition then
        :else else))

(new-if (> 2 1) :T :F)
(new-if (< 2 1) :T :F)

(defn iter [a b counter]
  (new-if (> a b)
          counter
          (iter (+ a 1) b (+ counter 1))))

; (iter 3 10 0)
; java.lang.StackOverflowError: null
; -> 무한반복!

(defmacro new-if [condition then else]
  `(cond ~condition ~then
         :else ~else))

(new-if (> 2 1) :T :F)
(new-if (< 2 1) :T :F)

; Macro를 쓰기 때문에, 여기서 iter를 다시 정의해야 함.
(defn iter [a b counter]
  (new-if (> a b)
          counter
          (iter (+ a 1) b (+ counter 1))))

(iter 3 10 0)
