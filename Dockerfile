FROM gostbaducking1/academy:latest

EXPOSE 8080

RUN ls && find . -name "i.jar"

ADD target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
