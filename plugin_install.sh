# /bin/bash

mvn install:install-file  -Dfile=lib/IKAnalyzer-3.2.8.jar  -DgroupId=org.wltea.analyzer  -DartifactId=IKAnalyzer -Dversion=3.2.8 -Dpackaging=jar

mvn install:install-file  -Dfile=lib/Sdk4J.jar  -DgroupId=com.qq  -DartifactId=connect -Dversion=2.0.0 -Dpackaging=jar

# java -javaagent:lib/springloaded-1.2.5.RELEASE.jar -noverify

# RUN apt-get install -y --no-install-recommends zip
# RUN apt-get update && apt-get install -y --no-install-recommends zip


mvn clean
mvn package

# 打包镜像
docker build --no-cache=true -t yidu:latest .
docker build --build-arg --no-cache=true -t yidu:latest .


# 运行容器
docker-compose up -d