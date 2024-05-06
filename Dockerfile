FROM openjdk:21

WORKDIR /src/main/java/com/autonetics/autonetics/api/

COPY . ./

RUN chmod 755 ./mvnw
RUN ./mvnw clean package

COPY . .

EXPOSE 8080:8080

CMD ["java" , "-jar", "./target/autonetics-rest-api-1.0.0.jar", "--server.port=8080"]