(defproject sladdit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :ring {:handler sladdit.app.handler/app}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-codec "1.0.0"]
                 [clj-http "1.1.2"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/java.jdbc "0.3.7"]
                 [honeysql "0.6.1"]
                 [org.xerial/sqlite-jdbc "3.8.7"]]
  :plugins [[lein-ring "0.9.6"]]
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
