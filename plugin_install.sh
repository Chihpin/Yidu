# /bin/bash

mvn install:install-file  -Dfile=lib/IKAnalyzer-3.2.8.jar  -DgroupId=org.wltea.analyzer  -DartifactId=IKAnalyzer -Dversion=3.2.8 -Dpackaging=jar

mvn install:install-file  -Dfile=lib/Sdk4J.jar  -DgroupId=com.qq  -DartifactId=connect -Dversion=2.0.0 -Dpackaging=jar

# mvn install:install-file  -Dfile=lib/springloaded-1.2.5.RELEASE.jar  -DgroupId=org.wltea.analyzer  -DartifactId=springloaded -Dversion=1.2.5 -Dpackaging=jar

# java -javaagent:lib/springloaded-1.2.5.RELEASE.jar -noverify

mvn clean

mvn package
mvn docker:build
mvn verify
mvn docker:push
mvn deploy

YiDuNovel:1.1.9-beta
docker build --build-arg HTTP_PROXY=http://127.0.0.1:7080 --no-cache=true -t yidu:latest .
docker build --build-arg HTTP_PROXY=http://172.16.1.13:7080 --no-cache=true -t yidu:latest .


# RUN apt-get install -y --no-install-recommends zip
# RUN apt-get update && apt-get install -y --no-install-recommends zip