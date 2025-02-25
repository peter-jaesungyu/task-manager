#!/bin/bash

# Set PATH based on the location of this script file
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
JAVA_CLASSPATH="$SCRIPT_DIR/out/production/task-cli"

# Run Java
java -classpath "$JAVA_CLASSPATH" TaskCliApplication "$@"
