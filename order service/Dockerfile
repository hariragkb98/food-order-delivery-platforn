# ---------- BUILD STAGE ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# copy only what is needed to build fast
COPY pom.xml .
COPY src ./src

# build jar (skip tests for speed)
RUN mvn -B -DskipTests package

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# copy the fat jar from the build stage (wildcard to match generated artifact)
COPY --from=build /app/target/*.jar app.jar

# recommended: tell java to use UTC or your timezone if needed
ENV TZ=Asia/Kolkata

ENTRYPOINT ["java","-jar","/app/app.jar"]