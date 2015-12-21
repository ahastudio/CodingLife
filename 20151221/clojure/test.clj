(ns test)
(remove-ns 'test)
(ns test)

(require '[clojure.string :as str])

(str/split "123.456" #"\.")

(defn new-version? [v1 v2]
  (defn to-numbers [version]
    (map #(Integer/parseInt %)
         (str/split version #"\.")))
  (defn check [v1 v2]
    (cond (or (empty? v1) (empty? v2)) false
          (> (first v1) (first v2)) true
          (< (first v1) (first v2)) false
          :else (check (next v1) (next v2))))
  (check (to-numbers v1) (to-numbers v2)))

(new-version? "0.0.2" "0.0.1")
(new-version? "1.0.10" "1.0.3")
(new-version? "1.2.0" "1.1.99")
(new-version? "1.1" "1.0.1")

(new-version? "0.1.3" "0.1.1")
(new-version? "0.1.3" "0.2.1")
(new-version? "0.1.3" "0.1.3")
