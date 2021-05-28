(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn find-index-max [vec]
  (first (apply max-key second (map-indexed vector vec))))

(defn possible-jumps [vec]
  (subvec vec 1 (inc (first vec))))

(defn find-index-max-ps-jump [vec]
  (inc (find-index-max (possible-jumps vec)))

(defn jump-game [vec]
  (cond (= (first vec) 0) false
        (= (count vec) 1) true
	:else (jump-game (subvec vec (find-index-max-ps-jump vec)))))
