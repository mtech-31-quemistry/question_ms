# Build and Generate Jar Stage
FROM gradle:8.7.0-jdk21 AS project-build
COPY . .

RUN gradle bootJar

RUN mv build/libs/question_ms.jar /usr/share/question_ms.jar
RUN mv src/main/resources/application.yml /usr/share/application.yml

FROM eclipse-temurin:21-alpine
COPY --from=project-build /usr/share/question_ms.jar .
COPY --from=project-build /usr/share/application.yml .

EXPOSE 80
CMD ["java", "-jar", "question_ms.jar" ]
