(defproject biographer "0.1.0"
  :description "Your GitHub bio, as-a-service."
  :url "https://github.com/jkrmr/biographer"

  :dependencies [[compojure "1.6.0"]
                 [conman "0.6.3"]
                 [cprop "0.1.10"]
                 [environ "1.1.0"]
                 [funcool/struct "1.0.0"]
                 [http-kit "2.2.0"]
                 [luminus-immutant "0.2.3"]
                 [luminus-migrations "0.3.3"]
                 [luminus-nrepl "0.1.4"]
                 [luminus/ring-ttl-session "0.3.2"]
                 [markdown-clj "0.9.99"]
                 [metosin/muuntaja "0.2.1"]
                 [metosin/ring-http-response "0.8.2"]
                 [mount "0.1.11"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.immutant/scheduling "2.1.6"]
                 [org.postgresql/postgresql "42.0.0"]
                 [org.webjars.bower/tether "1.4.0"]
                 [org.webjars/bootstrap "4.0.0-alpha.5"]
                 [org.webjars/font-awesome "4.7.0"]
                 [org.webjars/jquery "3.1.1"]
                 [org.webjars/webjars-locator-jboss-vfs "0.1.0"]
                 [ring-webjars "0.1.1"]
                 [ring/ring-core "1.6.0"]
                 [ring/ring-defaults "0.3.0"]
                 [selmer "1.10.7"]]

  :min-lein-version "2.0.0"
  :jvm-opts ["-server" "-Dconf=.lein-env"]
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource-paths ["resources"]
  :target-path "target/%s/"
  :main ^:skip-aot biographer.core
  :migratus {:store :database
             :db ~(get (System/getenv) "DATABASE_URL")}

  :plugins [[lein-cprop "1.0.1"]
            [lein-environ "1.1.0"]
            [lein-immutant "2.1.0"]
            [migratus-lein "0.4.7"]]

  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "biographer.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:dependencies [[prone "1.1.4"]
                                 [ring/ring-mock "0.3.0"]
                                 [ring/ring-devel "1.5.1"]
                                 [pjstadig/humane-test-output "0.8.1"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.19.0"]]

                  :source-paths ["env/dev/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:resource-paths ["env/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})
