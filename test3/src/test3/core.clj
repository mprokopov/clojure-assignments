(ns test3.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [test2.core :as profiles]
            [clojure.java.shell]
            [clojure.java.io :as io]
            [clojure.data.json :as data.json]
            [hiccup.core :as hiccup]))

(defmulti response
  "http router based on type :js, :json, :html"
  (fn [type _] type))

(defmethod response :json [type body]
  ;; returns json string from body
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (data.json/write-str body)})

(defmethod response :js [type body]
  ;; returns javascript as is
  {:status 200
   :headers {"Content-Type" "application/javascript"}
   :body body})

(defmethod response :html [type body]
  ;; converts body with hiccup syntax into text/html web server response
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (hiccup/html
          [:head [:script {:type "text/javascript" :src "/main.js"}]]
          [:body body])})

(defn handler
  "minimalistic router, not found redirects to root page"
  [{uri :uri}]
  (case uri
    "/" (response :html [:div#app "Loading ..."])
    "/users.json" (response :json (profiles/fetch-users 15))
    "/main.js" (response :js (slurp (io/resource "main.js")))
    "/about" (response :html [:p (:out (clojure.java.shell/sh "pwd"))])
    {:status 302 :headers {"Location" "/"}}))

(defn -main
  [& args]
  (jetty/run-jetty #'handler {:port 3000}))
