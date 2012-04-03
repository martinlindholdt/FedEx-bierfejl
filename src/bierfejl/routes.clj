(ns bierfejl.routes
  (:use compojure.core
        ring.middleware.resource
        ring.middleware.file-info
        ring.commonrest
        yousee-common.web
        [clojure.data.json :only (json-str)]
        bierfejl.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [clojure.data.json :as json]
            
            ))

(defroutes handler
  (GET ["/:context/ping", :context #".[^/]*"]  []
    {:status 200, :headers {"Content-Type" "text/plain" "expires" "0" "cache-control" "no-cache"} :body "pong" })

  (GET ["/:context/customer/:knr", :context #".[^/]*"] req 
    (let [body (find-by-customer (get-in req [:route-params :knr]))]
      {:status 200, :headers {"Content-Type" "text/plain" "expires" "0" "cache-control" "no-cache"} :body body}))

  (GET ["/:context/customer/:knr/json", :context #".[^/]*"] req 
    (let [body (find-by-customer (get-in req [:route-params :knr]))]
      ;(json-response body "application/json")
      (json-response body "application/json")
      ;{:status 200, :headers {"Content-Type" "text/plain" "expires" "0" "cache-control" "no-cache"} :body body}
      )) 
       
) ; end of (defroutes handler

(def app
  (-> (handler/site handler)
      (wrap-resource "public")
      (wrap-file-info)))

