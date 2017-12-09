(ns test3.profiles
  (:require [cheshire.core :as che]))

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
  in form {:name \"lastname firstname\" :email \"usermail@example.com\"}"
  ([] (fetch-users 5000)) ;; API call limited to 5000 users
  ([n]
   (let [api-uri (str *api-uri* "?results=" n)
         results (che/parse-string (slurp api-uri) true)]
     (map user-profile (results :results)))))
