(ns sladdit.core
  (:require [ring.util.codec :refer [form-encode]]
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [sladdit.app.config :refer [slack-webhook-url]]))

(def ^:dynamic send-slack #(client/post slack-webhook-url %))

(defn talk [msg]
  (send-slack
    {:body (json/write-str
            {:text (:msg msg) :username "sladdit" :channel (:channel msg)})})
  (:msg msg))
