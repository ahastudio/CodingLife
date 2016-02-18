(ns test)
(remove-ns 'test)
(ns test)

; 연습문제 2.20

(defn same-parity [x & numbers]
  (if (even? x)
    (cons x (filter even? numbers))
    (cons x (filter odd? numbers))))

(same-parity 1 2 3 4 5 6 7)
(same-parity 2 3 4 5 6 7)

(defn same-parity [& numbers]
  (filter (if (even? (first numbers)) even? odd?)
          numbers))

(same-parity 1 2 3 4 5 6 7)
(same-parity 2 3 4 5 6 7)

; 리스트 매핑(mapping)

(map + (list 1 2 3) (list 40 50 60) (list 700 800 900))

; map과 n-list를 이용해 zip 구현
(map list (list 1 2 3) (list 40 50 60) (list 700 800 900))
(map vector (list 1 2 3) (list 40 50 60) (list 700 800 900))
