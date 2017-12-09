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
    (= uri "/") (response [:a {:href "/users"} "Fetch 10 users"])
    :else {:status 302 :headers {"Location" "/"}}))

(defn -main
  [& args]
  (jetty/run-jetty #'handler {:port 3000}))
