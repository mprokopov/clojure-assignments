(ns test2.core-test
  (:require [clojure.test :refer :all]
            [test2.core :refer :all]))

(def mock-api3 {:results
                [{:email "jenny.hunt@example.com",
                  :phone "01141 407825",
                  :name {:title "mrs", :first "jenny", :last "hunt"},
                  :nat "GB",
                  :login
                  {:username "greengorilla589",
                   :password "1114",
                   :salt "k9ZNI7eL",
                   :md5 "e0d4252f72b8895520952fad6f2faecd",
                   :sha1 "eeaaeecb1337969e350f39612a58162ad4cea530",
                   :sha256
                   "9750be0a8aaa26f3b2930cd1b0f1e3be100321d823a7ff9074ed27dbdc871e3c"},
                  :dob "1995-02-26 10:39:00",
                  :id {:name "NINO", :value "LE 86 28 13 O"},
                  :picture
                  {:large "https://randomuser.me/api/portraits/women/49.jpg",
                   :medium "https://randomuser.me/api/portraits/med/women/49.jpg",
                   :thumbnail "https://randomuser.me/api/portraits/thumb/women/49.jpg"},
                  :gender "female",
                  :registered "2010-07-17 01:12:06",
                  :cell "0728-248-129",
                  :location
                  {:street "5210 school lane",
                   :city "salisbury",
                   :state "hertfordshire",
                   :postcode "S8 0PY"}}],
                :info {:seed "eff48748046e726d", :results 1, :page 1, :version "1.1"}})

(deftest user-profile-test
  (is (= (user-profile (first (mock-api3 :results))) {:name "hunt jenny" :email "jenny.hunt@example.com"})))

(deftest full-name-test
  (is (= (full-name {:first "Maksym" :last "Prokopov"}) "Prokopov Maksym")))
