FROM tomcat:9.0.0-jre8-alpine

MAINTAINER chihpin@users.noreply.github.com

ENV YIDU_HOME $CATALINA_HOME/yidu
ENV PATH $YIDU_HOME/scripts:$PATH

RUN mkdir -p $YIDU_HOME $YIDU_HOME/ROOT \
    && rm -r $CATALINA_HOME/webapps/ROOT/* \
    && mkdir -p $CATALINA_HOME/webapps/ROOT/books

COPY docker/yidu $YIDU_HOME
COPY target/YiDuNovel $YIDU_HOME/ROOT

RUN chmod +rw $YIDU_HOME \
        && chmod +x $YIDU_HOME/scripts/yidu_run.sh \
        && chmod +x $YIDU_HOME/scripts/yidu_config.sh

ENV YIDU_SITE_DOMAIN=localhost:8080 \
    YIDU_PATH_TXT=books/txt \
    YIDU_PATH_COVER=books/cover \
    YIDU_DB_HOST=localhost \
    YIDU_DB_PORT=5432 \
    YIDU_DB_NAME=yidu \
    YIDU_DB_USER=postgres \
    YIDU_DB_PWD=postgres

CMD ["yidu_run.sh"]