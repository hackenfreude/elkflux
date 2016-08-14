(defproject clojure-frontend "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring.middleware.logger "0.5.0"]
                 [clj-logging-config "1.9.12"]
                 [onelog "0.4.5"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler clojure-frontend.core/app})
