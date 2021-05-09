(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn should-keep? 
  [[first & rest]]
  (or (and (empty? rest) (pos? first))
      (< (Math/abs first) (reduce + rest))))

(defn reducing-dishes 
  ([index total satisfaction-sorted]
   (cond (empty? satisfaction-sorted) total
         (should-keep? satisfaction-sorted)
         (reducing-dishes (inc index) (+ total (* index (first satisfaction-sorted))) (rest satisfaction-sorted))
         :else (reducing-dishes index total (rest satisfaction-sorted))))) 

(def reducing-dishes-wrapper 
  (comp (partial reducing-dishes 1 0) sort))
