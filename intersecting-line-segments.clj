(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn does-intersect
  [first-point second-point]
  (< (max (get first-point 0) (get second-point 0)) (min (get first-point 1) (get second-point 1))))

(defn get-points
  [first-point second-point]
  (list (max (get first-point 0) (get second-point 0)) (min (get first-point 1) (get second-point 1))))

(defn get-points-checked
  "this probably is not the best way of doing this..."
  [first-point second-point]
  (if (does-intersect first-point second-point) (get-points first-point second-point)))

(defn is-first-to-increment [x y]
  "determine the array to get tail of"
  (if (< (get x 1) (get y 1))
    true
    false))

(defn get-all-intersections [first-line-segments second-line-segments intersections]
  "check intersecting line segments in linear time complexity"
  (if (or (empty? first-line-segments) (empty? second-line-segments))
    (remove empty? intersections)
    (if (is-first-to-increment (first first-line-segments) (first second-line-segments))
      (get-all-intersections (rest first-line-segments) second-line-segments (conj intersections (get-points-checked (first first-line-segments) (first second-line-segments))))
      (get-all-intersections first-line-segments (rest second-line-segments) (conj intersections (get-points-checked (first first-line-segments) (first second-line-segments)))))
    ))

(defn -main
  [& args]
  (println (get-all-intersections [[1 2] [3 6] [9 1000]] [[1 3] [5 7] [700 800] [850 861]] [])))


