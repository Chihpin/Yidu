FROM tomcat:7-jre8

MAINTAINER Cihh Pin <xxx.xxx@xxx.com>

# tomcat-users.xml sets up user accounts for the Tomcat manager GUI
# and script access for Maven deployments
ADD tomcat-users.xml $CATALINA_HOME/conf/



# create mount point for volume with application
RUN mkdir $CATALINA_HOME/webapps/yidu

# add tomcat jpda debugging environmental variables
#ENV JPDA_OPTS="-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n"
ENV JPDA_ADDRESS="8000"
ENV JPDA_TRANSPORT="dt_socket"

# start tomcat7 with remote debugging
EXPOSE 8080

CMD ["catalina.sh", "jpda", "run"]
