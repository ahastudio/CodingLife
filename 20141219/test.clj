(ns clojure.test.coding-dojang-20141219
  (:use clojure.test))

(def book-price 8)

(def packages
  [`(1 1 1 1 1)
   `(0 1 1 1 1)
   `(0 0 1 1 1)
   `(0 0 0 1 1)
   `(0 0 0 0 1)])

(defn discount-price
  [books]
  (let [count (reduce + books)]
    (* book-price
       count
       (get [1.00 1.00 0.95 0.90 0.80 0.75] count))))

(defn package?
  [books]
  (every? #(<= % 1) books))

(defn take_package?
  [books package]
  (and (> (reduce + package) 0)
       (every? #(>= % 0) (map - books package))))

(defn remain-books
  [books used-books]
  (map - books used-books))

(def total-price
  (memoize
   (fn [books]
     (if (package? books)
       (discount-price books)
       (let [sorted-books (sort books)]
         (apply min (for [package (filter #(take_package? sorted-books %) packages)]
                      (+ (total-price package)
                         (total-price (remain-books sorted-books package))))))))))

(is (= 51.20 (total-price `(2 2 2 1 1))))
(is (= 102.40 (total-price `(4 4 4 2 2))))
(is (= 8.00 (total-price `(1 0 0 0 0))))
(is (= 15.20 (total-price `(1 1 0 0 0))))
(is (= 21.60 (total-price `(1 1 1 0 0))))
(is (= 25.60 (total-price `(1 1 1 1 0))))
(is (= 30.00 (total-price `(1 1 1 1 1))))
(is (= 16.00 (total-price `(2 0 0 0 0))))
(is (= 23.20 (total-price `(2 1 0 0 0))))
(is (= 0.00 (total-price `(0 0 0 0 0))))
(is (= 150.00 (total-price `(5 5 5 5 5))))
(is (= 132.40 (total-price `(5 5 5 3 3))))
