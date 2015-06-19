(ns sladdit.model.channel-model
  (:require [clojure.java.jdbc :as jdbc]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]))

;;(require `(clojure.java [jdbc :as jdbc]))
;;(require `(honeysql.core :as sql))
;;(require `(honeysql.helpers :refer :all))

(def db-spec
  {:classname   "org.sqlite.JDBC"   
   :subprotocol "sqlite"
   :subname     "db/test.db"})

(defn- sql-get-for-token [token]
  (-> (select :*)
      (from :channels)
      (where [:= :token :?token])
      (sql/format :params {:token token})))

(defn get-for-token [token]
    (jdbc/query db-spec (sql-get-for-token token)))

(defn insert [token, webhook-url]
    (jdbc/insert! db-spec :channels {:token token :webhook_url webhook-url}))


; (defn- sql-insert [token, webhook-url]
;     (-> (insert-into :channels)
;         (columns :token :webhook-url)
;         (values
;             [[token webhook-url]])
;         sql/format))
;; (jdbc/query db-spec :channels ["INSERT INTO channels (token, webhook_url) VALUES (?, ?)" "asdf" "asdfasdf"])


