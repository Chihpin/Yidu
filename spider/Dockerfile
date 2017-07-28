FROM java:8-jre-alpine

MAINTAINER chihpin@users.noreply.github.com

ENV SPIDER_HOME /usr/local/spider
ENV PATH $SPIDER_HOME:$SPIDER_HOME/scripts:$PATH

RUN mkdir -p $SPIDER_HOME $SPIDER_TXT $SPIDER_COVER

WORKDIR $SPIDER_HOME

COPY . $SPIDER_HOME

ENV YIDU_DB_HOST="localhost" \
    YIDU_DB_PORT="5432" \
    YIDU_DB_NAME="yidu" \
    YIDU_DB_USER="postgres" \
    YIDU_DB_PWD="postgres"

# -rule demo.xml -ca
# -rule demo.xml -c 1-78000

ENV SPIDER_RULE='demo' \
    SPIDER_SITE_NAME='52YIDU' \
    SPIDER_SITE_HOST='http://localhost:8080' \
    SPIDER_RUN='-rule ${SPIDER_RULE}.xml -c 1-78000'
    
ENV SPIDER_BOOKS=$SPIDER_HOME/books \
    SPIDER_TXT="txt" \
    SPIDER_COVER="cover"

RUN mkdir -p $SPIDER_BOOKS/$SPIDER_TXT $SPIDER_BOOKS/$SPIDER_COVER

VOLUME $SPIDER_BOOKS

CMD ["start.sh"]