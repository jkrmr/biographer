(ns biographer.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[biographer started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[biographer has shut down successfully]=-"))
   :middleware identity})
