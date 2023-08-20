#!/bin/bash


if [ ! -d "classes" ]; then
  mkdir classes
fi
javac -cp "./lib/mysql-connector-java-8.0.33.jar" -d ./classes Server.java

java -cp "./lib/mysql-connector-java-8.0.33.jar:./classes" Server
