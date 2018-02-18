(defproject qcmd "0.1.0-SNAPSHOT"
  :description "Simple tool for storing terminal commands"
  :url "https://github.com/JaimeValdemoros/qcmd"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]]
  :plugins [[jonase/eastwood "0.2.5"]
            [lein-kibit "0.1.5"]]
  :aot [qcmd.core]
  :main qcmd.core)
