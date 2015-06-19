(ns sladdit.controller.channel-controller
  (:require [sladdit.model.channel-model :as channel-model]))

(defn get-for-token [token]
  (channel-model/get-for-token token))
