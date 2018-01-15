(set-env!
 :source-paths #{"src", "../test2/src"}
 ;; :source-paths #{"src"}
 :resource-paths #{"resources"}
 :dependencies '[[adzerk/boot-cljs "2.1.4" :scope "test"]
                 [adzerk/boot-reload "0.5.2" :scope "test"]
                 [org.clojure/test.check "0.9.0" :scope "test"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [cheshire "5.8.0"]
                 [org.clojure/clojurescript "1.9.521"]
                 [hiccup "1.0.5"]])

(task-options!
 pom {:project 'test3
      :version "1.0.0-SNAPSHOT"
      :description "FIXME: write description"}
 aot {:namespace '#{test3.core}}
 jar {:main 'test3.core}
 sift {:include #{#"\.jar$" #"main.js$"}})

(require '[adzerk.boot-cljs :refer [cljs]]
         '[adzerk.boot-reload :refer [reload]]
         '[test3.core :as app])

(deftask js []
  (comp
   (cljs :optimizations :advanced)
   (sift)
   (target)))

(deftask dev []
  (comp
   (cljs)
   (app/-main)))

(deftask build []
  (comp (cljs :optimizations :advanced)
     (aot)
     (pom)
     (uber)
     (jar)
     (sift)
     (target)))
