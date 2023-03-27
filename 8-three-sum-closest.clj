(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn get-subsets [n [f & r :as all]]
  (cond (= n 0) [[]]
        (empty? all) []
        :else (lazy-seq (concat (map #(cons f %) (get-subsets (dec n) r))
                                (get-subsets n r)))))

(defn three-sum-closest [l target]
  (->> l
       (get-subsets 3)
       (map #(apply + %))
       (#(for [i %] {:sum i :diff (abs (- i target))}))
       (sort-by :diff)
       first
       :sum))

(take 2 (get-subsets 2 (take 500000 (iterate inc 1))))
;; => ((1 2) (1 3))

(three-sum-closest [-1 2 1 -4] 1)
;; => 2
;; => 2

(three-sum-closest [1 1 1 0] -100)
;; => 2
;; => 2

(three-sum-closest [4,0,5,-5,3,3,0,-4,-5] -2)
;; => -2
;; => -2

(three-sum-closest [4,0,5,3,3,0,-4] -2)
;; => -1
;; => -1
