(ns day1.test.core
  (:use [day1.core])
  (:use [clojure.test]))

(deftest arebig
  (is (big "Four" 3))
  (is (big "Score" 4)))

(deftest arentbig
  (is (not (big "Four" 5)))
  (is (not (big "Four" 4))))


(deftest collections
  (is (= :list (collection-type (list 1 2 3))))
  (is (= :map (collection-type {:one 1, :two 2, :three 3})))
  (is (= :vector (collection-type [1 2 3]))))

(deftest notacollection
  (is (thrown? Exception (collection-type "a"))))
