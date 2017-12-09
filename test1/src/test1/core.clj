(ns test1.core
  (:require [clojure.set])
  (:gen-class))

(defn fizz-buzz
  "accepts number n,
  returns Fiss when argument has 3,
  Fuzz when argument has 5 and
  FizzBuzz when argument has 3 and 5,
  otherwise returns n"
  [n]
  (let [fizz-buzz-set (set (str n))]
    (cond
      (clojure.set/subset? #{\3 \5} fizz-buzz-set) "FizzBuzz"
      (fizz-buzz-set \3) "Fizz"
      (fizz-buzz-set \5) "Buzz"
      :else n)))

(defn -main
  [& args]
  (println
   (map fizz-buzz (range 100))))
