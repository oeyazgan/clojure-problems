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

(defn sum-tree
  "thanks to kobish"
  ([tree]
   (sum-tree tree 0))
  ([tree acc]
   (let [s (+ (* 10 acc)
              (:val tree))]
     (if (leaf? tree)
       s
       (+
        (sum-tree (:l tree) s)
        (sum-tree (:r tree) s))))))

(-> [1 2 3] vec-to-tree sum-tree)
;; => 25
(sum-tree {:val 1 :l {:val 2} :r {:val 3}} 0)
;; => 25

(-> [4 9 0 5 1] vec-to-tree sum-tree)
;; => 1026
(sum-tree
 {:val 4 :l {:val 9 :l {:val 5} :r {:val 1}} :r {:val 0}} 0)
;; => 1026
