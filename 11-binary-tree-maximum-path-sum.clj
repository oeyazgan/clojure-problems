(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn vec-to-tree
  ([vec]
   (vec-to-tree vec 0))
  ([vec i]
   (let [size (count vec)]
     (when (< i size)
       {:val (nth vec i)
        :l (vec-to-tree vec (inc (* 2 i)))
        :r (vec-to-tree vec (* 2 (inc i)))}))))

(defn leaf? [node]
  (and (nil? (:l node))
       (nil? (:r node))))

(defn get-max-line-fork-sums
  [{:keys [val r l] :as node}]
  (cond
    (or (nil? node) (nil? (:val node))) {:max-line Integer/MIN_VALUE
                                         :max-fork Integer/MIN_VALUE}
    (leaf? node) {:max-line val
                  :max-fork val}
    :else (let [[left-line left-fork] (-> l get-max-line-fork-sums vals)
                [right-line right-fork] (-> r get-max-line-fork-sums vals)
                max-fork (max left-fork right-fork (+ left-line right-line val))]
            {:max-line (max (+ left-line val) (+ right-line val))
             :max-fork max-fork})))

(defn max-path-sum [node]
  (let [{:keys [max-line max-fork]} (get-max-line-fork-sums node)]
    (max max-line max-fork)))

(max-path-sum {:val -10 :l {:val 9} :r {:val 20 :l {:val 15} :r {:val 7}}})
;; => 42

(max-path-sum {:val 1 :l {:val 2} :r {:val 3}})
;; => 6

(max-path-sum {:val 5
               :l {:val 2
                   :l {:val 3}
                   :r {:val -1}}
               :r {:val 4
                   :r {:val 7}
                   :l {:val -1}}})
;; => 21

(-> [5,4,8,11,nil,13,4,7,2,nil,nil,nil,1] vec-to-tree max-path-sum)
;; => 49

;; to any interested party
;; this is the initial solution I worked on
;; it is terrible, but also very beautiful at the same time. but more, terrible

;; (defn max-with-fork [forked cur-max max-both]
;;   (cond
;;     (> cur-max max-both) cur-max
;;     forked cur-max
;;     :else max-both))

;; (defn max-path-sum
;;   ([node] (max-path-sum node false))
;;   ([{:keys [val r l] :as node} forked]
;;    (cond
;;      (leaf? node) val
;;      (nil? (:val l)) (max
;;                       (max-path-sum r forked)
;;                       (+ val (max-path-sum r forked)))
;;      (nil? (:val r)) (max
;;                       (max-path-sum l forked)
;;                       (+ val (max-path-sum l forked)))
;;      :else (max-with-fork
;;             forked
;;             (max (max-path-sum r forked)
;;                  (max-path-sum l forked)
;;                  (+ val (max-path-sum r forked))
;;                  (+ val (max-path-sum l forked)))
;;             (+ val (max-path-sum r true) (max-path-sum l true))))))
