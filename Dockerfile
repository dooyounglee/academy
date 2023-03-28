FROM gostbaducking1/academy:latest

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
