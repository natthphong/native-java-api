#!/bin/bash

if [ -d "classes" ]; then
  echo "Remove classes"
    rm -rf classes
fi
mkdir -p classes
javac -d classes -cp "./lib/mysql-connector-j-8.0.33.jar" \
    Httpenum/*.java \
    configuration/*.java \
    controller/*.java \
    exception/*.java \
    model/*.java \
    repository/*.java \
    repository/Impl/*.java \
    service/*.java \
    utils/*.java \
    Server.java \
    ServerProcess.java

if [ $? -eq 0 ]; then
    echo "Compilation successful."

    # Run the Java program
    java -cp "classes:./lib/mysql-connector-j-8.0.33.jar" Server
else
    echo "Compilation failed."
fi
