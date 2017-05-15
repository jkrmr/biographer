(ns biographer.routes.home
  (:require [biographer.layout :as layout]
            [biographer.app.core :as app]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [ring.util.response :refer [redirect]]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render "home.html" {:biographies (app/get-all-bios)}))

(defn about-page []
  (layout/render "about.html"))

(defn add-bio [req]
    (let [content (get-in req [:params :content])]
      (app/insert-bio content)
      (app/update-gh-bio content)
      (redirect "/")))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (POST "/biographies" [] #(add-bio %)))
