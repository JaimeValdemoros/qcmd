lein = docker run -it --rm -v $(shell pwd):/usr/src/app:Z -w /usr/src/app clojure:lein-2.8.1-alpine lein

target/qcmd-%-standalone.jar: project.clj src/qcmd/core.clj
	$(lein) uberjar

main: target/qcmd-0.1.0-standalone.jar

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