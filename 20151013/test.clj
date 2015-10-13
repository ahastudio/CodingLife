; 그림판
; http://codingdojang.com/scode/483

(ns test)
(remove-ns 'test)
(ns test)

(defn lines-to-hash [lines]
  (let [height (count lines)]
    (defn iter [lines line x y data]
      (if (>= y height)
        data
        (if (empty? line)
          (iter (next lines) (first lines) 0 (+ y 1) data)
          (let [color (first line)]
            (iter lines
                  (next line)
                  (+ x 1)
                  y
                  (assoc data [x y] color))))))
    (iter (next lines) (first lines) 0 0 {})))

(defn hash-to-lines [data width height]
  (vec (map (fn [y]
              (apply str (map #(data [% y])
                              (range width))))
            (range height))))

(defn fill [x y color lines]
  (let [width (count (first lines))
        height (count lines)
        canvas (lines-to-hash lines)
        origin (canvas [x y])]
    (defn iter [canvas x y]
      (if (or (< x 0) (< y 0) (>= x width) (>= y height))
        canvas
        (if (= (canvas [x y]) origin)
          (reduce #(iter (assoc %1 [x y] color)
                         (+ x (%2 0))
                         (+ y (%2 1)))
                  canvas
                  [[1 0] [-1 0] [0 -1] [0 1]])
          canvas)))
    (hash-to-lines (iter canvas x y)
                   width
                   height)))

(defn lines-to-lines [lines]
  (hash-to-lines (lines-to-hash lines)
                 (count (first lines))
                 (count lines)))

(lines-to-lines ["001"
                 "010"
                 "100"])

(fill 0 0 \x
      ["000"
       "000"
       "000"])

(fill 0 0 \x
      ["001"
       "010"
       "100"])

; 문제에 있는 예제는 구멍이 있어서 전체가 채워지게 됨.
(= ["3333333333"
    "3333331333"
    "3333113133"
    "3311333313"
    "3133333313"
    "3133333313"
    "3133333133"
    "3313331333"
    "3331331333"
    "3333133333"]
   (fill 5 5 3
         ["0000000000"
          "0000001000"
          "0000110100"
          "0011000010"
          "0100000010"
          "0100000010"
          "0100000100"
          "0010001000"
          "0001001000"
          "0000100000"]))

; 예제 수정.
(= ["0000000000"
    "0000001000"
    "0000113100"
    "0011333310"
    "0133333310"
    "0133333310"
    "0133333100"
    "0013331000"
    "0001331000"
    "0000110000"]
   (fill 5 5 3
         ["0000000000"
          "0000001000"
          "0000110100"
          "0011000010"
          "0100000010"
          "0100000010"
          "0100000100"
          "0010001000"
          "0001001000"
          "0000110000"]))
