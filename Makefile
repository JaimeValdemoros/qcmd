lein = docker run -it --rm -v $(shell pwd):/usr/src/app:Z -w /usr/src/app clojure lein

target/qcmd-0.1.0-SNAPSHOT-standalone.jar: project.clj src/qcmd/core.clj
	$(lein) uberjar

main: target/qcmd-0.1.0-SNAPSHOT-standalone.jar

check:
	$(lein) check

repl:
	$(lein) repl

eastwood:
	$(lein) eastwood

kibit:
	$(lein) kibit

.PHONY: clean

clean:
	rm -rf target/*