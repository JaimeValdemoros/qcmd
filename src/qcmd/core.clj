(ns qcmd.core
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:import java.lang.Runtime
           (java.io BufferedReader InputStreamReader))
  (:gen-class))

(def help "HOW-TO: Choose between run, add and delete")

(def default-progs {"hello" ["echo Hello!"]})

(def config-location (str (System/getProperty "user.home") "/.config/qcmd/cmds.json"))

(defn read-config [] (if (.exists (io/file config-location)) (json/read-str (slurp config-location)) default-progs))

(defn write-config! [progs] (io/make-parents config-location) (spit config-location (json/write-str progs)))

(defn run-command [name args] (let [command ((read-config) name)
                                    ^String command-string (str/join " " (into command args))
                                    execution (.exec (Runtime/getRuntime) command-string)
                                    handle (BufferedReader. (InputStreamReader. (.getInputStream execution)))]
                                (print "Running commmand:" command)
                                (if (empty? args) (println) (println " with additional args" (vec args)))
                                (run! println (line-seq handle))
                                (shutdown-agents)
                                ))

(defn run [args] (case (second args)
                        "--" (run-command (first args) (rest (rest args)))
                        (run! #(run-command % []) args)))

(defn add [name command] (let [old-config (read-config)
                               new-config (conj old-config [name command])]
                           (write-config! new-config)))

(defn del [name] (let [old-config (read-config)
                       new-config (dissoc old-config name)]
                   (write-config! new-config)))

(defn list-cmds [] (run! (fn [[k v]] (println k ":" (str/join " " v))) (seq (read-config))))

(defn -main [& args] (case (first args)
                       ; Print the matching string from the default-progs dictionary
                       "run" (run (rest args))
                       ; Add a new command to the dictionary
                       "add" (add (second args) (rest (rest args)))
                       ; Delete a command from the dictionary
                       "del" (run! del (rest args))
                       ; List available commands
                       "list" (list-cmds)
                       ; Default: print help string
                       (println help)
                       ))