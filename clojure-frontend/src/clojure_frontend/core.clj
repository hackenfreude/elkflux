(ns clojure-frontend.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.logger :as logger]
            [onelog.core :as log])
  (:use
    clj-logging-config.log4j
    clojure.tools.logging))

(defn handle-get [item]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "<h1>We have " item "</h1>")})

(defroutes app-routes
  (GET "/:item" [item] (handle-get item))
  (route/not-found "Not Found"))

(def init
  (do
;    (set-config-logging-level! :debug)
    (set-logger!
      "onelog.core"
      :level :info
      :out (org.apache.log4j.FileAppender. (org.apache.log4j.PatternLayout. "%d %x %m %n") "/var/log/clojure-frontend/web.log" true))))

(def app
  (->
    (wrap-defaults app-routes site-defaults)
    (logger/wrap-with-logger)))
