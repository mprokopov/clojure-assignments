(ns test3.main
  (:require [goog.dom :as gdom]
            [goog.net.XhrIo :as xhr]
            [goog.object :as gobj]
            [rum.core :as rum]))

(def ^:dynamic *api-uri* "/users.json")

(defonce users (atom []))

(defn handler
  "resets users atom with data from JSON e"
  [e]
  (let [target (gobj/get e "target")
        users-json (.getResponseJson target)
        data (js->clj users-json :keywordize-keys true)]
    (reset! users data)))

(defn load-users
  "loads user list from *api-uri*"
  []
  (xhr/send *api-uri* handler))

(rum/defc users-list [state]
  [:.users
   [:h2 "Contacts list"]
   [:button {:on-click #(load-users) } "Reload"]
   [:ul
    (for [{:keys [name email]} state]
      [:li {:key email} name "-" email])]])

(defn main []
  (let [app-element (gdom/getElement "app")]
    ;; initial render
    (rum/mount (users-list) app-element)
    ;; render upon state updates
    (add-watch users
               :user-list-update
               (fn [_ _ _ new-state]
                 (rum/mount (users-list new-state) app-element)))
    ;; toggle data fetch
    (load-users)))

(main)
