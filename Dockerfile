FROM tomcat:9.0.0-jre8-alpine

MAINTAINER chihpin@users.noreply.github.com

ENV YIDU_HOME $CATALINA_HOME/yidu
ENV PATH $YIDU_HOME/scripts:$PATH


RUN mkdir -p $YIDU_HOME \ 
        && rm -r $CATALINA_HOME/webapps/ROOT

COPY target/YiDuNovel $CATALINA_HOME/webapps/ROOT

COPY docker/yidu $YIDU_HOME/

#RUN apk update && apk add zip bash && rm /var/cache/apk/* \
#        && dos2unix $YIDU_HOME/conf/* \
#        && dos2unix $YIDU_HOME/scripts/* \
#        && chmod +rw $YIDU_HOME \
#        && chmod +x $YIDU_HOME/scripts/yidu_run.sh \
#        && chmod +x $YIDU_HOME/scripts/yidu_config.sh


RUN dos2unix $YIDU_HOME/conf/* \
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

RUN mkdir -p $CATALINA_HOME/webapps/ROOT/$YIDU_BOOKS_PATH

VOLUME $CATALINA_HOME/webapps/ROOT/$YIDU_BOOKS_PATH

#CMD ["catalina.sh", "run"]
#CMD ["catalina.sh", "jpda", "run"]
CMD ["yidu_run.sh"]