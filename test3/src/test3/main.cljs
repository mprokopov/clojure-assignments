(ns test3.main
  (:require  [ajax.core :refer [GET]]
             [goog.dom :as gdom]
             [goog.object :as gobj]))

(defn user-list [m]
  (str "<ul>"
       (apply str
              (for [{:keys [name email]} m]
                (str "<li>" name "-" email "</li>")))
       "</ul"))

(defn handler [val]
  (let [app (gdom/getElement "app")]
    (gobj/set app "innerHTML" (user-list val))))

(GET "/users.json" {:handler handler
                    :response-format :json
                    :format :json
                    :keywords? true})
