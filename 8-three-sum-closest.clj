(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn get-three-combs [l]
  (let [size (count l)]
    (loop [first-index 0
           second-index 1
           rst (subvec l (+ 2 first-index))
           combs []]
      (cond
        (= first-index (- size 3)) (conj combs (vec (take-last 3 l)))
        (= second-index (- size 2)) (recur (inc first-index)
                                           (+ 2 first-index)
                                           (subvec l (+ 3 first-index))
                                           combs)
        (empty? rst) (recur first-index
                            (inc second-index)
                            (subvec l (+ 2 second-index))
                            combs)
        :else (recur first-index
                     second-index
                     (rest rst)
                     (conj combs [(nth l first-index)
                                  (nth l second-index)
                                  (first rst)]))))))

(defn three-sum-closest [l target]
  (->> l
       get-three-combs
       (map #(apply + %))
       (#(for [i %] {:sum i :diff (abs (- i target))}))
       (sort-by :diff)
       first
       :sum))

(three-sum-closest [-1 2 1 -4] 1)
;; => 2

(three-sum-closest [1 1 1 0] -100)
;; => 2

(three-sum-closest [4,0,5,-5,3,3,0,-4,-5] -2)
;; => -2

(three-sum-closest [4,0,5,3,3,0,-4] -2)
;; => -1
