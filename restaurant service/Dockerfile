# ---------- BUILD STAGE ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy restaurant service code
COPY pom.xml .
COPY src ./src

# 👇 Copy the locally built order-service JAR and POM into Maven's local repo
COPY order.service-0.0.1-SNAPSHOT.jar /root/.m2/repository/com/order.service/0.0.1-SNAPSHOT/order.service-0.0.1-SNAPSHOT.jar
COPY order-service-pom.xml /root/.m2/repository/com/order.service/0.0.1-SNAPSHOT/order.service-0.0.1-SNAPSHOT.pom

# Build restaurant service JAR
RUN mvn -B -DskipTests package

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the built fat JAR
COPY --from=build /app/target/*.jar app.jar

# Set timezone
ENV TZ=Asia/Kolkata

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
