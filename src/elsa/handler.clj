(ns elsa.handler
  (:use compojure.core
        ring.middleware.json)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [clojure.java.jdbc :as jdbc]
            [elsa.db :refer [db]]))

(defn test-db []
  (jdbc/query db "select count(*) from atomic.experiences_events"))


(defroutes app-routes
  (GET "/test" [to] (test-db))
  (route/not-found (response {:message "Page not found"})))


(defn wrap-log-request [handler]
  (fn [req]
    (println req)
    (handler req)))


(def app
  (-> app-routes
      wrap-log-request
      wrap-json-response
      wrap-json-body))
