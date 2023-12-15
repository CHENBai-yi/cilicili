# 指定基础映像
FROM openjdk:17-jdk-slim
USER root:root
# 维护者信息
LABEL maintainer="chenbaiyi.work@gmail.com"
# 设置工作目录
WORKDIR /cilicili

# 复制可运行JAR文件到容器
COPY build/libs/*.jar /cilicili/

# 暴露应用端口（如果需要）
EXPOSE 8888

# 容器启动命令
CMD java -jar *.jar