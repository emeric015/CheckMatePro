FROM maven:3.6.3-openjdk-17-temurin AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B package --file pom.xml -DskipTests

FROM openjdk:17-temurin
COPY --from=build /workspace/integration/target/*jar-with-dependencies.jar checkmate-pro.jar
EXPOSE 6379
ENTRYPOINT ["java","-jar","checkmate-pro.jar"]