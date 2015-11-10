(ns test)
(remove-ns 'test)
(ns test)

(= 'Hello 'Hello)
(= 'Hello 'Howdy)

(def x 'Hello)
(= 'Hello x)

(def x 'Howdy)
(= 'Hello x)

(defn reply [s]
  (cond (= s 'GoodMorning) 'Hi
        (= s 'HowAreYou?) 'Fine
        (= s 'GoodAfternoon) 'INeedANap
        (= s 'GoodEvening) 'BoyAmITired))

; 연습문제 5.1.1

(reply 'HowAreYou?)

(= 'Fine
   (reply 'HowAreYou?))


; 연습문제 5.1.2

(defn check-guess [guess target]
  (cond (< guess target) 'TooSmall
        (= guess target) 'Perfect
        (> guess target) 'TooLarge))

(= 'Perfect
   (check-guess 1 1))

(= 'TooLarge
   (check-guess 3 1))

(= 'TooSmall
   (check-guess 1 3))

; 연습문제 5.1.3

(defn check-guess3 [a b c target]
  (let [guess (+ a (* b 10) (* c 100))]
    (check-guess guess target)))

(= 'Perfect
   (check-guess3 0 0 1 100))

(= 'TooLarge
   (check-guess3 3 0 1 100))

(= 'TooSmall
   (check-guess3 0 8 0 100))

; 연습문제 5.1.4

(defn what-kind [a b c]
  (let [diff (- (* b b) (* 4 a c))]
    (cond (= a 0) 'degenerate
          (> diff 0) 'two
          (= diff 0) 'one
          (< diff 0) 'none)))

(= 'two
   (what-kind 1 0 -1))
(= 'one
   (what-kind 2 4 2))
(= 'none
   (what-kind 1 0 4))
(= 'degenerate
   (what-kind 0 1 1))
; => 이차 방정식이 아닌 경우를 확인할 수 있다.

; 연습문제 5.1.5

(defn check-color [target1 target2 guess1 guess2]
  (cond (and (= target1 guess1)
             (= target2 guess2)) 'Perfect
        (or (= target1 guess1)
            (= target2 guess2)) 'OneColorAtCorrentPosition
        (or (= target1 guess2)
            (= target2 guess1)) 'OneColorOccurs
        :else 'NothingCorrect))

(= 'Perfect
   (check-color 'red 'blue 'red 'blue))
(= 'OneColorAtCorrentPosition
   (check-color 'red 'blue 'red 'black))
(= 'OneColorOccurs
   (check-color 'red 'blue 'blue 'black))
(= 'NothingCorrect
   (check-color 'red 'blue 'green 'black))
