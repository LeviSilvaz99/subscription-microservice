FROM adoptopenjdk/openjdk11:alpine-jre

LABEL maintainer="levisilvaz99 <andrei_e.n.d@hotmail.com>"

COPY backend-consumer/target/backend-consumer-0.0.1-SNAPSHOT.jar backend-consumer.jar

CMD ["java", "-jar", "backend-consumer.jar"]