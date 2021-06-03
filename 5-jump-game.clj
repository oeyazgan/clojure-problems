(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn find-optimal-index [vec]
  (let [indexed-vec (map-indexed vector vec)
        vec-index-added-val (map (juxt first #(reduce + %)) indexed-vec)
        elem-optimal-jump (apply max-key second vec-index-added-val)]
    (first elem-optimal-jump)))

(defn possible-jumps [vec]
  (let [last-index (count vec)
        max-jump-range (->> vec first inc)]
    (subvec vec 1 (min last-index max-jump-range))))
  
(defn find-index-opt-ps-jump [vec]
  (->> vec
    possible-jumps
    find-optimal-index
    inc))

(defn jump-game [vec]
  (cond (= (first vec) 0) false
        (= (count vec) 1) true 
	:else (jump-game (subvec vec (find-index-opt-ps-jump vec)))))
