(ns test3.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [test3.profiles :as profiles]
            [hiccup.core :as hiccup]))

(defn response
  "converts body with hiccup syntax into text/html web server response"
  [body]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (hiccup/html
          [:head
           [:script {:type "text/javascript" :src "/main.js"}]]
          [:body body])})

(defn render-users
  "renders users collection m to hiccup syntax"
  [m]
  [:div
   [:h1 "User list"]
   [:ul
    (for [{:keys [name email]} m]
      [:li [:b name] " " email])]])

(defn handler
  "minimalistic router supports / and /users urls, otherwise redirects to root page"
  [{uri :uri}]
  (cond
    (= uri "/users") (-> (profiles/fetch-users 10)
                         render-users
                         response)
    (= uri "/users.json") {:status 200
                           :headers {"Content-type" "application/json"}
                           :body (cheshire.core/generate-string
                                  (profiles/fetch-users 10))}
    (= uri "/") (response [:div#app [:a {:href "/users"} "Fetch 10 users"]])
    (= uri "/main.js") {:status 200
                        :headers {"Content-type" "application/javascript"}
                        :body (slurp "resources/main.js")}
    :else {:status 302 :headers {"Location" "/"}}))

(defn -main
  [& args]
  (jetty/run-jetty #'handler {:port 3000}))
