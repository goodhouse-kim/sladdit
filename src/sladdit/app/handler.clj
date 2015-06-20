(ns sladdit.app.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.core :refer [defroutes POST]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [sladdit.core :refer [talk]]
            [compojure.route :as route]
            [clojure.string :as str]
            [sladdit.app.handle-list :as list]
            [sladdit.app.handle-submit :as submit]
            [sladdit.controller.channel-controller :as channel]))

(defn- handle-command [args username channel]
    (let [command (first (str/split args))
          args (subvec (str/split args) 1)]
        (if (= command "list")
            (list/handle channel args username)
            (if (= command "submit")
              (submit/handle channel args username)
              "Unknown Command"))))

(defn slash-command [request]
    (let [token (get-in request [:params :token])
          args  (get-in request [:params :text])
          username (get-in request [:params :user_name])]
        (->> (channel/get-for-token token)
             first
             (handle-command args username)
             talk)))

(defroutes app-routes
  (GET "/sladdit-slash-command" [] slash-command)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

;(GET "/hello/:name" [name] (str "Hello " name))