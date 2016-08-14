(ns clojure-frontend.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.logger :as logger])
  (:use
    clj-logging-config.log4j
    clojure.tools.logging))

(defn handle-get []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<h1>Hello World</h1>"})

(defroutes app-routes
  (GET "/" [] (handle-get))
  (route/not-found "Not Found"))

(def init
  (do
;    (set-config-logging-level! :debug)
    (set-logger!
      "onelog.core"
      :level :info
      :out (org.apache.log4j.FileAppender. (org.apache.log4j.PatternLayout. "%d %m %n") "/var/log/clojure-frontend/web.log" true))))

(def app
  (->
    (wrap-defaults app-routes site-defaults)
    (logger/wrap-with-logger)))
