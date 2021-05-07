(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn should-keep? 
  [[first & rest]]
  (cond (and (empty? rest) (pos? first))
        true
        :else (< (Math/abs first) (reduce + rest))))

(defn reducing-dishes 
  ([index total satisfaction-sorted]
  (cond (empty? satisfaction-sorted) total
        (should-keep? satisfaction-sorted)
        (reducing-dishes (+ index 1) (+ total (* index (first satisfaction-sorted))) (rest satisfaction-sorted))
        :else (reducing-dishes index total (rest satisfaction-sorted))))) 

(defn reducing-dishes-wrapper 
  [satisfaction]
  (reducing-dishes 1 0 (sort satisfaction)))
