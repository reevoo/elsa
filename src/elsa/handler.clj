(ns elsa.handler
  (:use compojure.core
        ring.middleware.json
        ring.middleware.params)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [clojure.java.jdbc :as jdbc]
            [elsa.db :refer [db]]
            [clojure.data.json :as json]))

(defn test-db [trkref reviewable-context]
  (jdbc/query db ["SELECT DISTINCT page_url FROM atomic.mark_events WHERE collector_tstamp > getdate() - 30 AND trkref = ? AND reviewable_context = ?"
                  trkref
                  (json/write-str reviewable-context)]))


(defroutes app-routes
  (GET "/product_url" [trkref & reviewable-context] (test-db trkref reviewable-context))
  (route/not-found (response {:message "The only supported endpoint is /product_url"})))


(defn wrap-log-request [handler]
  (fn [req]
    (println req)
    (handler req)))


(def app
  (-> app-routes
      wrap-params
      wrap-log-request
      wrap-json-response
      wrap-json-body))
