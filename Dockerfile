# Java 21 JDK tabanlı bir image kullan
FROM eclipse-temurin:21-jdk

# Uygulamanın JAR dosyasını kopyala
COPY target/renart-project-0.0.1-SNAPSHOT.jar app.jar

# Spring Boot uygulamasını başlat
ENTRYPOINT ["java", "-jar", "app.jar"]
