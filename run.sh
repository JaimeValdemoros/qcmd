#!/bin/bash

make
java -jar target/qcmd-*-SNAPSHOT-standalone.jar "$@"
