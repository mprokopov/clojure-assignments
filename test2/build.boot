(set-env!
 :source-paths #{"src"}
 :dependencies '[[adzerk/boot-test "1.2.0" :scope "test"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]])

(require '[adzerk.boot-test :refer [test]]
         'test2.core)

(deftask testing []
  (set-env! :source-paths #{"src" "test"})
  (comp (test)))
