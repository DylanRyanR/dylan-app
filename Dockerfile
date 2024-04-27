FROM openjdk:17.0.2-jdk
MAINTAINER dylan
ADD ./ruoyi-admin/target/ruoyi-admin.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar"]
CMD ["app.jar"]
