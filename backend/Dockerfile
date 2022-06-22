# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER zc
# VOLUME 指定临时文件目录为/tmp，在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为transport-backend.jar
ADD target/backend-0.0.1-SNAPSHOT.jar transport-backend.jar
# 运行jar包
RUN bash -c 'touch /transport-backend.jar'
ENTRYPOINT ["java","-jar","/transport-backend.jar"]
#暴露8080端口作为微服务
EXPOSE 8080