(ns sladdit.app.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.core :refer [defroutes POST]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [sladdit.app.handle-list :as list]
            [sladdit.app.handle-submit :as submit]
            [sladdit.controller.channel-controller :as channel]))

(defn- handle-command [args token]
    (let [command (first args)]
        (if (= command "list")
            (list/handle args token)
            (if (= command "submit")
              (submite/handle args token)
              "Unknown Command")
            )))



(defn slash-command [request]
    (let [token (get-in request [:params :token])
          args  (get-in request [:params :text])]
        (->> (channel/get-for-token token)
            first
            (handle-command args))))

(defroutes app-routes
  (GET "/sladdit-slash-command" [] slash-command)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

;(GET "/hello/:name" [name] (str "Hello " name))