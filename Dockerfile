FROM gostbaducking1/academy:latest

EXPOSE 8080

ADD target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
