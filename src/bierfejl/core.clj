(ns bierfejl.core

	(:require 	[clojure.java.jdbc :as sql]

				clj-time.core))

(defn ping [msg]
	(prn "pong " msg)
	msg
	)

(def mysql-db {:subprotocol "mysql"
    :subname "//xxx.xxx.xxx.xxx:3306/fejl"
    :user "username"
    :password "password"})

;(def dates (:tid_afsluttet :tid_oprettet))
(def fields '(:status :anlaegsnummer :prioritet :person :kundenr :fejlkode :hf_nummer))

(defn find-by-customer [kundenr] 
	(prn "Finding " kundenr)
	(sql/with-connection mysql-db 
	    (sql/with-query-results rows
	        ;["SELECT * FROM fejl WHERE kundenr = ? LIMIT 0, 100" kundenr]
	        ["SELECT status, anlaegsnummer, prioritet, person, kundenr, fejlkode, hf_nummer FROM fejl WHERE kundenr = ? LIMIT 0, 100" kundenr]
	        

;; DIV. eksperimenter med at konvertere mySql timestamps til noget der kan konverteres til json. Der var ikke tid til at få det til at virke.
	        ;(prn (first rows))
	        ;(prn (:status (first rows)))
	        ;(println "hej")
	        
	        ; (do (map (fn [[k v]] (if (= (:tid_afsluttet k)) (assoc [k v] k "dato"))  rows)))

	        ;(do (map (fn [[k v]] (if (= (:tid_afsluttet k)) (prn v)))  (first rows)))

;  	        (do (map (fn [[k v]] (if (= (:tid_afsluttet k)) (prn (type v) v )))  (first rows)))

  	     ;   (do (map (fn [[k v]] (prn (= (str (type k)) "java.sql.Timestamp")  (str (type v)) k )) (first rows)))

  	        ;(do (map (fn [[k v]] (if (= (str (type v)) java.sql.Timestamp) (prn (type v)))) (first rows)))

  	        ;	java.sql.Timestamp


  	        ;(let [res  (map (fn [[k v]] (if (contains? fields k) {k v}) ) (first rows))]

   	        ; (let [res  (map (fn [[k v]] (if (contains? fields k) v (prn "x" k)) ) (first rows))]
  	        	; (prn res)
  	        	; (prn fields)
  	        	; res) 

   	        (let [return (vec rows)]
   	        	return)
	        ;(prn "returning:")
	        ;(prn (count rows))
	        ;(prn (vec (first rows))
	        ;(println (vec (map vec rows)))

	        ; (:cost (first rows))
	        ;(vec (first rows)) 

	        ;rows
	        )))

