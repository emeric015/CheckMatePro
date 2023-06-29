FROM maven:3.8.3-openjdk-17 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY checkmatepro-engine /workspace/checkmatepro-engine
COPY checkmatepro-viewer /workspace/checkmatepro-viewer
COPY integration /workspace/integration
RUN mvn -B package --file pom.xml -DskipTests

FROM openjdk:17
COPY --from=build /workspace/integration/target/*jar-with-dependencies.jar checkmate-pro.jar
EXPOSE 6379
ENTRYPOINT ["java","-jar","checkmate-pro.jar"]