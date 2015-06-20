(ns sladdit.controller.post-controller
  (:require [sladdit.model.post-model :as post-model]))

(defn insert-new [channel-id submitter body]
  (post-model/insert channel-id submitter body (quot (System/currentTimeMillis) 1000)))

(defn get-top-ten [channel-id]
  (post-model/get-top-ten channel-id))

(defn get-hot-ten [])

(defn get-new-ten [])
