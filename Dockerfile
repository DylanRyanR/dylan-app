FROM openjdk:17.0.2-jdk
MAINTAINER dylan
# 添加时区环境变量，亚洲，上海
ENV TimeZone=Asia/Shanghai
# 使用软连接，并且将时区配置覆盖/etc/timezone
RUN ln -snf /usr/share/zoneinfo/$TimeZone /etc/localtime && echo $TimeZone > /etc/timezone
ADD ./ruoyi-admin/target/ruoyi-admin.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar"]
CMD ["app.jar"]
