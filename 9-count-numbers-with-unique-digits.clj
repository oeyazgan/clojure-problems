(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

;; 0 -> 0
;; 1 -> 10
;; 2 -> 9 . 9
;; 3 -> 9 . 9. 8
(defn count-numbers-with-unique-digits [n]
  (condp = n
    0 1
    1 10
    2 (* 9 9)
    3 (* 9 9 8)
    4 (* 9 9 8 7)
    5 (* 9 9 8 7 6)
    6 (* 9 9 8 7 6 5)
    7 (* 9 9 8 7 6 5 4)
    8 (* 9 9 8 7 6 5 4 3)))

(defn count-all-digit-counts [n]
  (loop [digit n acc 0]
    (if (zero? digit)
      acc
      (recur (dec digit)
             (+ acc (count-numbers-with-unique-digits digit))))))

(count-all-digit-counts 3)
;; => 739
