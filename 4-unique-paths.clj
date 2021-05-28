(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn unique-paths [m n] 
  (if (or (= m 1) (= n 1)) 1
  (+ (unique-paths (dec m) n) (unique-paths m (dec n)))))
