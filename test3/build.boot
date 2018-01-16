(set-env!
 :source-paths #{"src" "../test2/src"}
 :resource-paths #{"resources"}
 :dependencies '[[adzerk/boot-cljs "2.1.4" :scope "test"]
                 [adzerk/boot-reload "0.5.2" :scope "test"]
                 [adzerk/boot-test "1.2.0" :scope "test"]
                 ;[org.clojure/test.check "0.10.0-alpha2" :scope "test"]
                 [pandeiro/boot-http "0.8.3" :scope "test"]
                 [tonsky/boot-anybar "0.1.0" :scope "test"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/clojurescript "1.9.946"]
                 [org.clojure/clojure "1.8.0"]
                 [hiccup "1.0.5"]])

(task-options!
 pom {:project 'test3
      :version "1.0.0-SNAPSHOT"
      :description "FIXME: write description"}
 aot {:namespace '#{test3.core}}
 jar {:main 'test3.core}
 sift {:include #{#"\.jar$"}})

(require '[adzerk.boot-cljs :refer [cljs]]
         '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-test :refer [test]]
         '[pandeiro.boot-http :refer [serve]]
         '[tonsky.boot-anybar :refer [anybar]]
         'test3.core)

(deftask js []
  (comp (cljs :optimizations :advanced)
     (target)))

;; (deftask run []
;;   (comp (with-pass-thru _ (test3.core/-main))
;;      (cljs :optimizations :advanced)
;;      (target)))

(deftask dev []
  (comp (serve :handler 'test3.core/handler)
     (watch)
     (notify)
     (cljs :optimizations :advanced)
     (target)))

(deftask testing []
  (set-env! :source-paths #(conj % "test"))
  (comp (watch)
     (anybar)
     (notify :audible true)
     (test)))

(deftask build []
  (comp (cljs :optimizations :advanced)
     (aot) (pom) (uber) (jar)
     (sift)
     (target)))
