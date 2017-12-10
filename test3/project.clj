(defproject test3 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "1.1.7"]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.521"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [cheshire "5.8.0"]
                 [hiccup "1.0.5"]
                 [cljs-ajax "0.7.3"]]
  :main ^:skip-aot test3.core
  :target-path "target/%s"
  :cljsbuild {
              :builds [{
                        :source-paths ["src"]
                        :compiler {
                                   :output-to "resources/main.js"
                                   :optimizations :advanced
                                   :pretty-print true}}]}
  :profiles {:uberjar {:aot :all}})
