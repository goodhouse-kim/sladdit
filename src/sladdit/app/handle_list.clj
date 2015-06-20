(ns sladdit.app.handle-list
  (:require [sladdit.controller.channel-controller :as channel-controller]
            [sladdit.controller.post-controller :as post-controller]))

(defn handle [channel args username]
  (if channel
    {:msg (post-controller/get-top-ten (:channel_id channel)) :channel (:channel_name channel)}
    {:msg "Token is not authorized" :channel nil}))
