(ns test1.core-test
  (:require [clojure.test :refer :all]
            [test1.core :refer :all]))

(deftest fizz-buzz-test
  (testing "FizzBuzz"
    (is (= (fizz-buzz 3) "Fizz"))
    (is (= (fizz-buzz 5) "Buzz"))
    (is (= (fizz-buzz 35) "FizzBuzz"))
    (is (= (fizz-buzz 48) 48))))

