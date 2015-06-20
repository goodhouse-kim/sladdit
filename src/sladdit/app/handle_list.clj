(ns sladdit.app.handle-list
  (:require [sladdit.controller.channel-controller :as channel-controller]
            [sladdit.controller.post-controller :as post-controller]))

(defn handle [channel args username]
  (if channel
    (post-controller/get-top-ten)
    "Token is not authorized"))
