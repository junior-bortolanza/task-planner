FROM  gradle:8.10-jdk21 AS build

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/build/libs/agendador-tarefas-0.0.1-SNAPSHOT.jar /app/agendador-tarefas.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/agendador-tarefas.jar"]