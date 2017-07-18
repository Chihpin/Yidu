FROM java:8-jre-alpine

MAINTAINER chihpin@users.noreply.github.com

ENV SPIDER_HOME=/usr/local/spider
ENV SPIDER_WEBROOT=/usr/local/spider
ENV PATH $SPIDER_HOME:$PATH
RUN mkdir -p $SPIDER_HOME && mkdir -p $SPIDER_WEBROOT

WORKDIR $SPIDER_HOME

COPY . $SPIDER_HOME

ENV YIDU_DB_HOST="localhost" \
    YIDU_DB_PORT="5432" \
    YIDU_DB_NAME="yidu" \
    YIDU_DB_USER="postgres" \
    YIDU_DB_PWD="postgres"

ENV SPIDER_SITE_NAME='52YIDU' \
    SPIDER_SITE_HOST='http://localhost:8080' \
    SPIDER_TXT='files/article/txt' \
    SPIDER_COVER='files/article/cover'

VOLUME ['/usr/local/spider/WEBROOT']

CMD ["./start.sh"]