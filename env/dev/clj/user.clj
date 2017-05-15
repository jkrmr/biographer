(ns user
  (:require [mount.core :as mount]
            biographer.core))

(defn start []
  (mount/start-without #'biographer.core/repl-server))

(defn stop []
  (mount/stop-except #'biographer.core/repl-server))

(defn restart []
  (stop)
  (start))


