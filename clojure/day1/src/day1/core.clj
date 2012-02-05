(ns day1.core)

(defn big [word size] (> (count word) size))

(defn collection-type [c] 
(cond
  (list? c) :list
  (map? c) :map
  (vector? c) :vector)
)
