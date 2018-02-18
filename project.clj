(defproject qcmd "0.1.0-SNAPSHOT"
  :description "Simple tool for storing terminal commands"
  :url "https://github.com/JaimeValdemoros/qcmd"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/core.match "0.3.0-alpha5"]]
  :plugins [[jonase/eastwood "0.2.5"]
            [lein-kibit "0.1.5"]]
  :local-repo "target/m2"
  :aot [qcmd.core]
  :main qcmd.core)
