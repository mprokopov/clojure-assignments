(ns test3.main
  (:require  [ajax.core :refer [GET]]
             [goog.dom :as gdom]
             [goog.object :as gobj]))

(defn user-list
  "returns HTML string from users maps list m"
  [m]
  (str "<ul>"
       (apply str
              (for [{:keys [name email]} m]
                (str "<li>" name "-" email "</li>")))
       "</ul>"))

(defn handler
  "renders user-list from JSON val onto #app element"
  [val]
  (let [app-element (gdom/getElement "app")]
    (gobj/set app-element "innerHTML" (user-list val))))

(GET "/users.json" {:handler handler
                    :response-format :json
                    :format :json
                    :keywords? true})
