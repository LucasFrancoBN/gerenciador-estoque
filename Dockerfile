# build
FROM maven:3.9.11-amazoncorretto-24 as BUILD
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:24-jdk
WORKDIR /app
COPY --from=build ./build/target/*.jar ./app.jar

ENTRYPOINT java -jar app.jar
