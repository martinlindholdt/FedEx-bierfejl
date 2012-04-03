(ns jetty
  (:use bierfejl.routes
	ring.adapter.jetty)
 (:require [clojure.tools.logging :as logging]
           [clojure.tools.logging.impl :as impl])
  (:import (org.mortbay.xml XmlConfiguration)
	   (org.mortbay.jetty.webapp WebAppContext)))

(defn init-server [server]
  (try
    (alter-var-root (var logging/*logger-factory*) (constantly (impl/log4j-factory)))
    (let [config (XmlConfiguration. (slurp "test/jetty.xml"))]    
      (. config configure server))
    (catch Exception e
      (prn "Unable to load jetty configuration")
      (. e printStackTrace))))


(comment (defn boot []
  (future (run-jetty #'app {:port 8080 :configurator init-server}))))

(defonce server
  (run-jetty #'app {:port 8080 :configurator init-server :join? false}))  
