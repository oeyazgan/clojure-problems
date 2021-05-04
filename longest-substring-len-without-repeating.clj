(ns ahmet-buhanla-zihinden-clojure.core
  (:gen-class))

(defn longest-substring-len-without-repeating
  ([str]
   (longest-substring-len-without-repeating str (hash-set) 0 0))
  ([str current-max-chars current-max current-count]
   (let [current-char (first str)
         char-exists (contains? current-max-chars current-char)]  
     (cond
       (empty? str) current-max
       char-exists (longest-substring-len-without-repeating (rest str) (hash-set current-char) (max current-max 1) 1)
       :else 
       (do 
         (let [new-current (+ 1 current-count)]
           (longest-substring-len-without-repeating (rest str) (conj current-max-chars current-char) (max current-max new-current) new-current)
           ))))))
