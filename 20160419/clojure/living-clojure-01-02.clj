(ns test)
(remove-ns 'test)
(ns test)

; http://www.4clojure.com/problem/13

(= [20 30 40]
   (rest [10 20 30 40]))

; http://www.4clojure.com/problem/14

(= 8
   ((fn add-five [x] (+ x 5)) 3))

(= 8
   ((fn [x] (+ x 5)) 3))

(= 8
   (#(+ % 5) 3))

(= 8
   ((partial + 5) 3))

; http://www.4clojure.com/problem/15

(def f #(* 2 %))

(= (f 2) 4)

(= (f 3) 6)

(= (f 11) 22)

(= (f 7) 14)

; http://www.4clojure.com/problem/16

(def f #(str "Hello, " % "!"))

(= (f "Dave") "Hello, Dave!")

(= (f "Jenn") "Hello, Jenn!")

(= (f "Rhea") "Hello, Rhea!")

; http://www.4clojure.com/problem/17

(= '(6 7 8)
   (map #(+ % 5) '(1 2 3)))

; http://www.4clojure.com/problem/18

(= '(6 7)
   (filter #(> % 5) '(3 4 5 6 7)))

; http://www.4clojure.com/problem/35

(= 7
   (let [x 5]
     (+ 2 x)))

(= 7
   (let [x 3, y 10]
     (- y x)))

(= 7
   (let [x 21]
     (let [y 3]
       (/ x y))))

; http://www.4clojure.com/problem/36

(= 10
   (let [x 7 y 3 z 1]
     (+ x y)))

(= 4
   (let [x 7 y 3 z 1]
     (+ y z)))

(= 1
   (let [x 7 y 3 z 1]
     z))
