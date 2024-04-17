FROM openjdk:latest
COPY ./target/sem-cw.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-cw.jar", "db:3306", "10000"]