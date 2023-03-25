(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn divide-numbers [divident divisor]
  (let [factor
        (cond
          (and (neg? divident) (pos? divisor)) -1
          (and (pos? divident) (neg? divisor)) -1
          :else 1)]
    (loop [result 0 accumulated 0]
      (if (>= (+ accumulated (abs divisor)) (abs divident))
        (* factor result)
        (recur (inc result) (+ accumulated (abs divisor)))))))
