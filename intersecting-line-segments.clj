(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn does-intersect
  [first-point second-point]
  (< (max (get first-point 0) (get second-point 0)) (min (get first-point 1) (get second-point 1))))

(defn get-points-naive
  [first-point second-point]
  (list (max (get first-point 0) (get second-point 0)) (min (get first-point 1) (get second-point 1))))

(defn get-intersections
  "this probably is not the best way of doing this..."
  [first-point second-point]
  (if (does-intersect first-point second-point) (get-points-naive first-point second-point)))

(defn is-first-to-increment [x y]
  "determine the array to get tail of"
  (< (second x) (second y)))

(defn get-all-intersections
  ([all-segments]
   (get-all-intersections (first all-segments) (second all-segments)))
  ([first-segments second-segments]
   (get-all-intersections first-segments second-segments []))
  ([first-segments second-segments intersections]
   "check intersecting line segments in linear time complexity"
   (if (or (empty? first-segments) (empty? second-segments))
     (remove empty? intersections)
     (let [new-intersections (conj intersections (get-intersections (first first-segments) (first second-segments)))]
       (if (is-first-to-increment (first first-segments) (first second-segments))
         (get-all-intersections (rest first-segments) second-segments new-intersections)
         (get-all-intersections first-segments (rest second-segments) new-intersections)))
     )))

(defn in? 
  [coll elm]  
  (some #(= elm %) coll))

(defn custom-split [my-vec character] 
  [(subvec my-vec 0 (.indexOf my-vec character)) (subvec my-vec (+ 1 (.indexOf my-vec character)))]) 

(defn dispatch-input-format 
  ([inp]
   (cond  
     (contains? inp :first) :inp-map
     (reduce #(and %1 %2) [(vector? (first inp)) (vector? (second inp)) ((complement in?) inp "|")]) :inp-standard 
     (and (vector? inp) (in? inp "|")) :inp-vector)
   ))

(defmulti normalize-input dispatch-input-format)
(defmethod normalize-input :inp-standard [inp] inp)
(defmethod normalize-input :inp-map [inp]
  (vector (get inp :first) (get inp :second)))
(defmethod normalize-input :inp-vector [inp] (custom-split inp "|"))

(defn -main
  [& args]
  (def my-standard-vec [[[1 2] [3 6] [9 1000]] [[1 3] [5 7] [700 800] [850 861]]]) 
  (def my-vec [[1 2] [3 6] [9 1000] "|" [1 3] [5 7] [700 800] [850 861]]) 
  (def my-map {:first [[1 2] [3 6] [9 1000]] :second [[1 3] [5 7] [700 800] [850 861]]}) 
  (println (get-all-intersections (normalize-input my-standard-vec)))
  (println (get-all-intersections (normalize-input my-vec)))
  (println (get-all-intersections (normalize-input my-map))))

