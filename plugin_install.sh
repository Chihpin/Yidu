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


# https://forums.docker.com/t/host-path-of-volume/12277/3
# You need to keep in mind that Docker is still running inside a VM. The system paths are still relative to the VM, and not to your Mac.
# Only some folders are already mounted from your Mac into the VM. You can get an overview by running this command:
docker run --rm -it -v /:/vm-root alpine:edge ls -l /vm-root

docker run --rm -it -v /usr/local/yidu:/vm-root alpine:edge ls -l /vm-root

docker run --rm -it -v /var/lib/docker/volumes/yidu_yidu_volume/_data:/vm-root alpine:edge ls -l /vm-root

git config --global user.email 'chihpin@users.noreply.github.com'
