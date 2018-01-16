(ns test2.core
  (:gen-class)
  (:require [clojure.data.json :as data.json]))

(def ^:dynamic *api-uri* "https://randomuser.me/api/")

(defn full-name
  "accepts map with keys first and last and returns full user name"
  [{:keys [first last]}]
  (str last " " first))

(defn user-profile
  "accepts map m and returns map with keys
  :name and :email"
  [m]
  (let [{:keys [name email]} m]
    {:name (full-name name) :email email}))

(defn fetch-users
  "queries external API for users profiles and returns lazy sequence
  in form {:name \"lastname firstname\" :email \"usermail@example.com\"}
  if n supplied, queries only n results, otherwise returns indefinite sequence"
  ([] (repeatedly (fn []
                    (let [api-uri *api-uri*
                          json (data.json/read-str (slurp api-uri) :key-fn keyword)
                          result (first (:results json))]
                      (user-profile result)))))
  ([n]
   (let [api-uri (str *api-uri* "?results=" n)
         results (data.json/read-str (slurp api-uri) :key-fn keyword)]
     (map user-profile (results :results)))))

(defn -main
  [& args]
  (do
    (println "Infinite sequence take 15")
    (time
     (println (take 15 (fetch-users))))

    (println "Query exactly 15 entries")
    (time
     (println (fetch-users 15)))))

