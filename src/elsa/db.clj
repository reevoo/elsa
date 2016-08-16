(ns elsa.db
  (:require [environ.core :refer [env]]))


(def db
  {:connection-uri (env :snowplow-db-uri)})
