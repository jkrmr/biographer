(ns biographer.routes.home
  (:require [biographer.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [environ.core :refer [env]]
            [ring.util.http-response :as response]
            [ring.util.response :refer [redirect]]
            [clojure.java.io :as io]
            [clojure.data.json :as json]
            [org.httpkit.client :as http]))

(def github-api-token (env :github-api-token))
(def github-api-version "application/vnd.github.v3+json")
(def github-api-base-url "https://api.github.com" )
(def github-api-user-path "/user")

(defn home-page []
  (layout/render "home.html" {:biographies ["one" "two"]}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (POST "/biographies" []
        (fn [req]
          (let [content (get-in req [:params :content])
                options {:headers {"Accept" github-api-version
                                   "Authorization" (str "token " github-api-token)}
                         :body (json/write-str {"bio" content})}]
            (http/patch (str github-api-base-url github-api-user-path) options
                      (fn [{:keys [status headers body error]}]
                        (if error
                          (println "Failed: " error)
                          (println "Success. Updated bio to : " content))))
            (redirect "/")))))
