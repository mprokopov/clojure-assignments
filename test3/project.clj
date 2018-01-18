(defproject test3 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.521"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [org.clojure/data.json "0.2.6"]
                 [hiccup "1.0.5"]
                 [rum "0.10.8"]]
  :plugins  [[lein-figwheel "0.5.8"]
             [lein-cljsbuild "1.1.7"]
             [lein-ring "0.9.7"]]
  :ring {:handler test3.core/handler}
  :figwheel {:ring-handler test3.core/handler}
  :main ^:skip-aot test3.core
  :source-paths ["src" "../test2/src"]
  :resource-paths ["resources"]
  :target-path "target/%s"
  :cljsbuild {
              :builds [
                       {:id "dev"
                        :source-paths ["src"]
                        ;; :figwheel true
                        :figwheel { :on-jsload "test3.main/on-js-reload"}
                        :compiler {:main test3.main
                                   :asset-path "out"
                                   :output-to "resources/public/main.js"
                                   :output-dir "resources/public/out"
                                   :source-map-timestamp true}}
                       {:id "min"
                        :source-paths ["src"]
                        :compiler {
                                   :output-to "resources/public/main.js"
                                   :optimizations :advanced
                                   :pretty-print true}}]}
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [figwheel-sidecar "0.5.8"]]}})
  
