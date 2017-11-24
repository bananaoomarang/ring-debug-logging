(ns ring-debug-logging.core
  (:require
   [io.aviso.ansi :refer :all]))

(defn- get-req-method [request]
  (-> (:request-method request)
      (name)
      (clojure.string/upper-case)))

(defn- get-req-body! [request]
  (-> (:body request)
      (slurp)))

(defn- refill-req-with-body [request body-str]
  (assoc request :body (java.io.ByteArrayInputStream. (.getBytes body-str))))

(defn- get-status-color [status]
  (condp > status
    300 green
    400 red
    500 blue
    600 red
    :else red))

(defn- print-res-status [{:keys [status] :as response}]
  (println status)
  (println (< status 500))
  (print
   ((get-status-color status) status) ""))

(defn- print-req-method [request res-status]
  (let [method   (get-req-method request)
        good-res (< res-status 300)
        color    (get-status-color res-status)]

    (print
     (-> request
         (get-req-method)
         (color)
         (bold)) "")))

(defn- print-body [body]
  (print
   (if (empty? body)
     (cyan "[no body]")
     (bold-white body)) ""))

(defn wrap-with-logger [handler]
  (fn [request]
    (let [req-body (get-req-body! request)
          req-orig (if (empty? req-body)
                     request
                     (refill-req-with-body request req-body))
          response (handler req-orig)]
      (print-res-status response)
      (print-req-method request (:status response))
      (print-body req-body)
      (print ":: ")
      (print-body (:body response))
      (println))))
