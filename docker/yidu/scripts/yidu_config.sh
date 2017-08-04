#!/bin/sh

Green='\033[0;32m'
Clear='\033[0m'

echo -e "${Green}----[Config]----------------------------------------------------------------------------------${Clear}"


YIDU_SITE_URL=http://${YIDU_SITE_DOMAIN}


cd $YIDU_HOME

# ------------------------------------------------------------------------------------------------------
# copy www
# ------------------------------------------------------------------------------------------------------


dir=$CATALINA_HOME/webapps/ROOT
echo "Folder 'ROOT' copying ..."
mv $YIDU_HOME/ROOT/* $dir/

# ------------------------------------------------------------------------------------------------------
# update jdbc properties
# ------------------------------------------------------------------------------------------------------

jdbc=$CATALINA_HOME/webapps/ROOT/WEB-INF/classes/jdbc.properties
dos2unix ${jdbc}

sed -i "s,driverClassName=.*$,driverClassName=org.postgresql.Driver,g" ${jdbc}
sed -i "s,url=.*$,url=jdbc:postgresql:\/\/${YIDU_DB_HOST}:${YIDU_DB_PORT}\/${YIDU_DB_NAME},g" ${jdbc}
sed -i "s,username=.*$,username=${YIDU_DB_USER},g" ${jdbc}
sed -i "s,password=.*$,password=${YIDU_DB_PWD},g" ${jdbc}

echo '-----'
cat ${jdbc}
echo '-----'



# ------------------------------------------------------------------------------------------------------
# update yidu properties
# ------------------------------------------------------------------------------------------------------

yidu=$CATALINA_HOME/webapps/ROOT/WEB-INF/classes/yidu.properties
dos2unix ${yidu}
sed -i "s,uri=.*$,uri=${YIDU_SITE_URL},g" ${yidu}
sed -i "s,mobileUri=.*$,mobileUri=${YIDU_SITE_URL},g" ${yidu}
sed -i "s,filePath=.*$,filePath=${CATALINA_HOME}/webapps/ROOT/${YIDU_PATH_TXT},g" ${yidu}
sed -i "s,relativeIamgePath=.*$,relativeIamgePath=/${YIDU_PATH_COVER},g" ${yidu}


echo '-----'
cat ${yidu} | head -n 8
echo '-----'



# ------------------------------------------------------------------------------------------------------
# update package properties
# ------------------------------------------------------------------------------------------------------

package=$CATALINA_HOME/webapps/ROOT/WEB-INF/classes/language/package.properties

dos2unix ${package}
UP_DOMAIN=$(echo ${YIDU_SITE_DOMAIN} | tr 'a-z' 'A-Z')

# 不能这么做 因为 多语言文件 最后需要的是  Unicode 的，否则读取时会出错 native2ascii
# label_system_title=${YIDU_SITE_TITLE}'|免费小说网|手打小说网'
# label_system_siteKeywords=${YIDU_SITE_TITLE}',就爱读书网,免费小说网,无弹出广告小说网,手打小说网'
# label_system_siteDescription=${YIDU_SITE_TITLE}'提供玄幻小说,言情小说,网游小说,修真小说,都市小说,武侠小说,网络小说等在线阅读,我们是更新最快,免费最多,页面简洁且无弹出广告的小说网站!'

# sed -ig "s>label\.system\.title=.*$>label\.system\.title=${label_system_title}>g" ${package}
# sed -ig "s>label\.system\.siteKeywords=.*$>label\.system\.siteKeywords=${label_system_siteKeywords}>g" ${package}
# sed -ig "s>label\.system\.siteDescription=.*$>label\.system\.siteDescription=${label_system_siteDescription}>g" ${package}

# sed -ig "s>label\.system\.name=.*$>label\.system\.name=${YIDU_SITE_TITLE}>g" ${package}

sed -ig "s>label\.system\.url=.*$>label\.system\.url=${YIDU_SITE_URL}>g" ${package}
sed -ig "s>label\.system\.domain=.*$>label\.system\.domain=${YIDU_SITE_DOMAIN}>g" ${package}
# sed -ig "s>label\.system\.copyright=.*$>label\.system\.copyright=Copyright © 2017-2018 ${UP_DOMAIN} All Rights Reserved.>g" ${package}
# sed -ig 's>label\.system\.support=.*$>label\.system\.support=Powered by <a href="${YIDU_SITE_URL}">易读  V1.1.9 Beta</a>>g' ${package}


echo '-----'
cat ${package} | head -n 12
echo '-----'



ls -l $CATALINA_HOME/webapps/ROOT

# Not working: jar tool is part of jdk
# jar -uvf $CATALINA_HOME/webapps/ROOT.war WEB-INF/classes/jdbc.properties

# does not deploy (unpack) ROOT.war
# ls $CATALINA_HOME/webapps/ROOT

# cp ${jdbc} $CATALINA_HOME/webapps/ROOT/WEB-INF/classes/jdbc.properties

# echo $(whoami)

# https://stackoverflow.com/questions/45104097/dockerize-tomcat-web-app-with-custom-configuration-via-env
# https://superuser.com/questions/413888/how-to-update-one-file-located-in-a-zip-subdirectory
# zip -ur $CATALINA_HOME/webapps/ROOT.war WEB-INF


# mkdir test_war
# cd test_war
# unzip $CATALINA_HOME/webapps/ROOT.war

rm -r $YIDU_HOME/WEB-INF
cd $CATALINA_HOME
# ------------------------------------------------------------------------------------------------------
# ------------------------------------------------------------------------------------------------------
echo -e "${Green}----[Config][Success]----------------------------------------------------------------------------------${Clear}"