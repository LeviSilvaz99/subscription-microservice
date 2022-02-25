FROM adoptopenjdk/openjdk11:alpine-jre

LABEL maintainer="levisilvaz99 <andrei_e.n.d@hotmail.com>"

COPY backend-productor/target/backend-productor-0.0.1-SNAPSHOT.jar backend-productor.jar

CMD ["java", "-jar", "backend-productor.jar"]