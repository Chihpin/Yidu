FROM tomcat:9.0.0-jre8-alpine

MAINTAINER YiDuNovel

ENV YIDU_HOME $CATALINA_HOME/yidu
ENV PATH $YIDU_HOME/scripts:$PATH

RUN mkdir -p $YIDU_HOME \ 
        && rm -r $CATALINA_HOME/webapps/ROOT

ADD target/YiDuNovel.war $CATALINA_HOME/webapps/ROOT.war

COPY docker/ $YIDU_HOME/

RUN apk update && apk add zip bash && rm /var/cache/apk/* \
        && dos2unix $YIDU_HOME/conf/* \
        && dos2unix $YIDU_HOME/scripts/* \
        && chmod +rw $YIDU_HOME \
        && chmod +x $YIDU_HOME/scripts/yidu_run.sh \
        && chmod +x $YIDU_HOME/scripts/yidu_config.sh


ENV YIDU_DB_HOST="localhost" \
    YIDU_DB_PORT="5432" \
    YIDU_DB_NAME="yidu" \
    YIDU_DB_USER="postgres" \
    YIDU_DB_PWD="postgres"

VOLUME ["/usr/local/yidu"]

CMD ["yidu_run.sh"]