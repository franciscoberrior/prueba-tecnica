# Etapa de construcción
FROM eclipse-temurin:17-jdk AS build
WORKDIR /build
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa final (solo JRE)
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/*.jar /app/applicationClient.jar
CMD java -jar applicationClient.jar
