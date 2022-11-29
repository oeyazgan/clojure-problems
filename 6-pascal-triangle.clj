(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn pascal-triangle [x]
  (cond (= x 1) [1]
        :else (let [start [1]
                    end [1]
                    middle (map #(apply + %) (partition 2 1 x))]
                (concat start middle end))))

(def all-pascal-triangles (iterate pascal-triangle [1]))

(take 30 all-pascal-triangles)
