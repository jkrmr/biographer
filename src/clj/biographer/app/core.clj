(ns biographer.app.core
  (:require [environ.core :refer [env]]
            [clojure.data.json :as json]
            [clojure.java.jdbc :as db]
            [org.httpkit.client :as http]))

(def github-api-token
  (env :github-api-token))

(def github-api-version
  "application/vnd.github.v3+json")

(def github-api-base-url
  "https://api.github.com" )

(def github-api-user-path
  "/user")

(def database
  (env :database-url))

(defn insert-bio [input]
  "Insert INPUT into the biographies table."
  (db/insert! database :biographies {:content input}))

(defn get-all-bios []
  "Query the database for all biography content, returning a vector of strings."
  (db/query database ["select content from biographies"]))

(defn get-random-bio []
  (first (db/query database ["SELECT content FROM biographies OFFSET floor(random()*(select count(*) from biographies)) LIMIT 1"])))

(defn update-gh-bio [content]
  (let [options {:headers {"Accept" github-api-version
                           "Authorization" (str "token " github-api-token)}
                 :body (json/write-str {"bio" content})}
        url (str github-api-base-url github-api-user-path)]
    (http/patch url options
                (fn [{:keys [status headers body error]}]
                  (if error (println "Failed: " error))))))

(defn update-gh-bio-with-random-bio []
  (let [bio (:content (get-random-bio))]
    (println (format "Posting '%s' to GitHub..." bio))
    (update-gh-bio bio)))
