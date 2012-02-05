(ns day1.test.core
  (:use [day1.core])
  (:use [clojure.test]))

(deftest arebig
  (is (big "Four" 3))
  (is (big "Score" 4)))

(deftest arentbig
  (is (not (big "Four" 5)))
  (is (not (big "Four" 4))))

