main: project.clj src/qcmd/core.clj
	lein compile && lein uberjar

.PHONY: clean

clean:
	rm -f target/*.jar