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

(defn get-all-intersections [first-segments second-segments intersections]
  "check intersecting line segments in linear time complexity"
  (if (or (empty? first-segments) (empty? second-segments))
    (remove empty? intersections)
    (let [new-intersections (conj intersections (get-intersections (first first-segments) (first second-segments)))]
      (if (is-first-to-increment (first first-segments) (first second-segments))
        (get-all-intersections (rest first-segments) second-segments new-intersections)
        (get-all-intersections first-segments (rest second-segments) new-intersections)))
    ))

(defn -main
  [& args]
  (println (get-all-intersections [[1 2] [3 6] [9 1000]] [[1 3] [5 7] [700 800] [850 861]] [])))

