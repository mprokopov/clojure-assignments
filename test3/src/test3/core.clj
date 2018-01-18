(ns test3.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :as middleware.resource]
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
          [:head]
          [:body body
           [:script {:type "text/javascript" :src "/main.js"}]])})

(defn handler
  "minimalistic router, not found redirects to root page"
  [{uri :uri :as req}]
  (condp re-matches uri
    #"/users.json" (response :json (profiles/fetch-users 15))
    #"/" (response :html [:div#app "Loading ..."])
    nil))

    ;{:status 302 :headers {"Location" "/"}}))

(def app-handler (middleware.resource/wrap-resource #'handler "public"))

(defn -main
  [& args]
  (jetty/run-jetty (middleware.resource/wrap-resource #'handler "public") {:port 3000}))
