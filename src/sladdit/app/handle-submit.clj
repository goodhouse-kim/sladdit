(ns sladdit.app.handle-submit
  (:require [sladdit.controller.channel-controller :as channel-controller
             sladdit.controller.post-controller :as post-controller]))

(defn handle [token args username]
  (if token
    (let [channel (channel-controller/get-for-token token)]
      (post-controller/insert-id (:channel-id channel) username ((clojure.string/join " " args)))
    "Token is not authorized"))