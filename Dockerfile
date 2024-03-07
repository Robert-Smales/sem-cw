FROM openjdk:latest
COPY ./target/sem-cw-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-cw-0.1.0.1-jar-with-dependencies.jar"]