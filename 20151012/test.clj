; CamelCase를 Pothole_case로 바꾸기!
; http://codingdojang.com/scode/484

(ns reload.core)

(defn characters [s]
  (seq s))

(defn upper-to-underscore [x]
  (if (or (Character/isUpperCase x) (Character/isDigit x))
    (str \_ (clojure.string/lower-case x))
    x))

(defn camel-case-to-pothole-case [s]
  (apply str (map upper-to-underscore (characters s))))

(= "coding_dojang"
   (camel-case-to-pothole-case "codingDojang"))

(= "num_goat_3_0"
   (camel-case-to-pothole-case "numGoat30"))
