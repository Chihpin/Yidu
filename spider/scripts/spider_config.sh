    #!/bin/bash

    Green='\033[0;32m'
    Clear='\033[0m'

    echo -e "${Green}----[Config]----------------------------------------------------------------------------------${Clear}"
    
    dos2unix conf/*

    # ------------------------------------------------------------------------------------------------------
    # update jdbc properties
    # ------------------------------------------------------------------------------------------------------
    
    jdbc=jdbc.properties

    sed -i "s,driverClassName=*,driverClassName=org.postgresql.Driver,g" ${jdbc}
    sed -i "s,url=*,url=jdbc:postgresql:\/\/${YIDU_DB_HOST}:${YIDU_DB_PORT}\/${YIDU_DB_NAME},g" ${jdbc}
    sed -i "s,username=*,username=${YIDU_DB_USER},g" ${jdbc}
    sed -i "s,password=*,password=${YIDU_DB_PWD},g" ${jdbc}


    # echo '-----'
    # echo ${jdbc}
    # echo '-----'

    # ------------------------------------------------------------------------------------------------------
    # ------------------------------------------------------------------------------------------------------


    # ------------------------------------------------------------------------------------------------------
    # update jdbc properties
    # ------------------------------------------------------------------------------------------------------
    
    site=site.ini


    sed -i "s,local_site_url=*,local_site_url=${SPIDER_SITE_HOST},g" ${site}
    sed -i "s,local_site_name=*,local_site_name=${SPIDER_SITE_NAME},g" ${site}
    sed -i "s,txt_file=*,txt_file=${SPIDER_WEBROOT}/${SPIDER_TXT},g" ${site}
    sed -i "s,cover_dir=*,cover_dir=${SPIDER_WEBROOT}/${SPIDER_COVER},g" ${site}

    
    # echo '-----'
    # echo ${site}
    # echo '-----'

    # ------------------------------------------------------------------------------------------------------
    # ------------------------------------------------------------------------------------------------------
    echo -e "${Green}----[Config][Success]----------------------------------------------------------------------------------${Clear}"