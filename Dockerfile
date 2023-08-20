FROM openjdk:19

WORKDIR /app

COPY . /app

RUN mkdir -p classes

RUN javac -d classes -cp "./lib/mysql-connector-j-8.0.33.jar" \
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

CMD ["java", "-cp", "classes:./lib/mysql-connector-j-8.0.33.jar", "Server"]
