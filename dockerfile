FROM openjdk:23-ea-17-jdk-oraclelinux8
COPY build/libs/quemiztry_question_ms.jar /quemiztry_question_ms.jar
COPY src/main/resources/application.properties /application.properties
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/quemiztry_question_ms.jar"]
