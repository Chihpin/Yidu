FROM tomcat:9.0.0-jre8-alpine

MAINTAINER chihpin@users.noreply.github.com

ENV YIDU_HOME $CATALINA_HOME/yidu
ENV PATH $YIDU_HOME/scripts:$PATH

RUN mkdir -p $YIDU_HOME $YIDU_HOME/ROOT

COPY docker/yidu $YIDU_HOME
COPY target/YiDuNovel $YIDU_HOME/ROOT

#RUN apk update && apk add zip bash && rm /var/cache/apk/* \
#        && dos2unix $YIDU_HOME/conf/* \
#        && dos2unix $YIDU_HOME/scripts/* \
#        && chmod +rw $YIDU_HOME \
#        && chmod +x $YIDU_HOME/scripts/yidu_run.sh \
#        && chmod +x $YIDU_HOME/scripts/yidu_config.sh


RUN apk update && apk add bash && rm /var/cache/apk/* \
        && dos2unix $YIDU_HOME/conf/* \
        && dos2unix $YIDU_HOME/scripts/* \
        && chmod +rw $YIDU_HOME \
        && chmod +x $YIDU_HOME/scripts/yidu_run.sh \
        && chmod +x $YIDU_HOME/scripts/yidu_config.sh

ENV YIDU_DB_HOST="localhost" \
    YIDU_DB_PORT="5432" \
    YIDU_DB_NAME="yidu" \
    YIDU_DB_USER="postgres" \
    YIDU_DB_PWD="postgres" \
    YIDU_BOOKS_PATH="books"

#CMD ["catalina.sh", "run"]
#CMD ["catalina.sh", "jpda", "run"]
CMD ["yidu_run.sh"]
# CMD ["/usr/local/tomcat/yidu/scripts/yidu_run.sh"]