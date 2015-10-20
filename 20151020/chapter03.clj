(ns htdp)
(remove-ns 'htdp)
(ns htdp)

; 3장 - 프로그램은 함수 + 변수 정의다


; 연습문제 3.1.3

(defn attendees [ticket-price]
  (+ 120
     (* (/ 15 0.10)
        (- 5.00 ticket-price))))

(defn revenue [ticket-price]
  (* (attendees ticket-price)
     ticket-price))

(defn cost [ticket-price]
  (+ 180
     (* 0.04 (attendees ticket-price))))

(defn profit [ticket-price]
  (- (revenue ticket-price)
     (cost ticket-price)))

(profit 3.00)
(profit 4.00)
(profit 5.00)

; 연습문제 3.1.4

(defn cost [ticket-price]
  (* 1.50 (attendees ticket-price)))

(profit 3.00)
(profit 4.00)
(profit 5.00)

; 연습문제 3.2.1

(def DEFAULT-TICKET-PRICE 5.00)
(def DEFAULT-ATTENDEES 120)
(def ATTENDEES-PER-UNIT-PRICE 15)
(def UNIT-PRICE 0.10)
(def FIXED-COST 180)
(def COST-PER-ATTENDEE 0.04)

(defn attendees [ticket-price]
  (+ DEFAULT-ATTENDEES
     (* (/ ATTENDEES-PER-UNIT-PRICE UNIT-PRICE)
        (- DEFAULT-TICKET-PRICE ticket-price))))

(defn revenue [ticket-price]
  (* (attendees ticket-price)
     ticket-price))

(defn cost [ticket-price]
  (+ FIXED-COST
     (* COST-PER-ATTENDEE (attendees ticket-price))))

(defn profit [ticket-price]
  (- (revenue ticket-price)
     (cost ticket-price)))

(profit 3.00)
(profit 4.00)
(profit 5.00)

; 연습문제 3.2.1 + 연습문제 3.1.4

(def FIXED-COST 0)
(def COST-PER-ATTENDEE 1.50)

(profit 3.00)
(profit 4.00)
(profit 5.00)

; 연습문제 3.3.1

(defn inches->cm [x]
  (* 2.54 x))

(defn feet->inches [x]
  (* 12 x))

(defn yards->feet [x]
  (* 3 x))

(defn rods->yards [x]
  (* (+ 5 (/ 1 2)) x))

(defn furlongs->rods [x]
  (* 40 x))

(defn miles->furlongs [x]
  (* 8 x))

(defn feet->cm [x]
  (inches->cm (feet->inches x)))

(defn yards->cm [x]
  (feet->cm (yards->feet x)))

(defn rods->inches [x]
  (feet->inches (yards->feet (rods->yards x))))

(defn miles->feet [x]
  (yards->feet (rods->yards (furlongs->rods (miles->furlongs x)))))

(feet->cm 1)
(yards->cm 1)
(rods->inches 1)
(miles->feet 1)

; 연습문제 3.3.2

(def PI 3.14)

(defn volume-cylinder [radius height]
  (* (* PI radius radius)
     height))

(volume-cylinder 3 5)

; 연습문제 3.3.3

(defn area-cylinder [radius height]
  (+ (* 2 (* PI radius radius))
     (* (* 2 PI radius) height)))

(volume-cylinder 3 5)

; 연습문제 3.3.4

(defn area-cylinder-side [radius height]
  (* (* 2 PI radius) height))

(defn area-of-disk [r]
  (* PI r r))

(defn area-of-ring [outer inner]
  (- (area-of-disk outer)
     (area-of-disk inner)))

(defn area-pipe [radius length thickness]
  (+ (area-cylinder-side radius length)
     (area-cylinder-side (+ radius thickness) length)
     (area-of-ring (+ radius thickness) radius)))

(area-pipe 3 5 1)

; 연습문제 3.3.5

(def g 100)

(defn height [t]
  (* (/ 1 2) (* g t) t))

(height 5)

; 연습문제 3.3.6

(defn Fahrenheit->Celsius [fahrenheit]
  (/ (- fahrenheit 32) 1.8))

(defn Celsius->Fahrenheit [celsius]
  (+ 32 (* celsius 1.8)))

(defn I [f]
  (Celsius->Fahrenheit (Fahrenheit->Celsius f)))

(I 1)
(I 2)
(I 3)
