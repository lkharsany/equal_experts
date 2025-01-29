FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY target/githubApiApp-0.0.1-SNAPSHOT.jar /app/githubApiApp.jar

CMD ["java", "-jar", "githubApiApp.jar"]
