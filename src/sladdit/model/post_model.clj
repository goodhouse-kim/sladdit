(ns sladdit.model.post-model  
  (:require [clojure.java.jdbc :as jdbc]
            [honeysql.core :as sql]
            [honeysql.helpers :refer :all]))
;; (require '[clojure.java.jdbc :as j])

(def db-spec
  {:classname   "org.sqlite.JDBC"   
   :subprotocol "sqlite"
   :subname     "db/test.db"})

(defn- sql-get-top-ten [channel_id]
  ["SELECT v.post_id, SUM(v.vote_type), p.body 
    FROM votes v 
    INNER JOIN posts p ON v.post_id=p.post_id 
    WHERE v.post_id IN 
      (SELECT post_id 
        FROM posts 
        WHERE channel_id=?) 
    GROUP BY v.post_id 
    ORDER BY SUM(v.vote_type) 
    LIMIT 10", channel_id])

(defn get-top-ten []
  (jdbc/query db-spec (sql-get-top-ten)))

(defn insert [channel-id submitter body timestamp]
  (jdbc/insert! db-spec :posts {:channel_id channel-id :submitter submitter :body body :submitted_time timestamp}))