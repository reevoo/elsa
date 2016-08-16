(defproject elsa "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.5.1"]
                 [cheshire "5.6.3"]
                 [org.clojure/java.jdbc "0.6.2-alpha1"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]
                 [environ "1.1.0"]]

  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]
         :plugins [[lein-ring "0.9.7"]]
         :ring {:handler elsa.handler/app
                :nrepl {:start? true
                        :port 9998}}}})
