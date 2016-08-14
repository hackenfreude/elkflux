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
    (set-loggers!
      :root {:out :console :level :error}
      "onelog.core" {:out :console :level :info})
    (info "logging initialized")))

(def app
  (->
    (wrap-defaults app-routes site-defaults)
    (logger/wrap-with-logger)))
