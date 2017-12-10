(ns test3.main
  (:require [goog.dom :as gdom]
            [goog.net.XhrIo]
            [goog.object :as gobj]))

(defn user-list
  "returns HTML string from users list of maps m"
  [m]
  (str "<ul>"
       (apply str
              (for [{:keys [name email]} m]
                (str "<li>" name "-" email "</li>")))
       "</ul>"))

(defn handler
  "renders user-list onto #app element, used as callback function"
 [e]
 (let [target (gobj/get e "target")
       users-json (.getResponseJson target)
       users (js->clj users-json :keywordize-keys true)
       app-element (gdom/getElement "app")]
   (gobj/set app-element "innerHTML" (user-list users))))

(goog.net.XhrIo/send "/users.json" handler)
