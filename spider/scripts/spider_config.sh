    #!/bin/bash

    Green='\033[0;32m'
    Clear='\033[0m'


    echo -e "${Green}----[Config]----------------------------------------------------------------------------------${Clear}"
    
    mkdir -p ${SPIDER_WWW}/${SPIDER_TXT}
    mkdir -p ${SPIDER_WWW}/${SPIDER_COVER}
    # ------------------------------------------------------------------------------------------------------
    # update jdbc properties
    # ------------------------------------------------------------------------------------------------------
    cd ${SPIDER_HOME}
    jdbc=${SPIDER_HOME}/jdbc.properties
    dos2unix ${jdbc}

    sed -i "s,driverClassName=.*$,driverClassName=org.postgresql.Driver,g" ${jdbc}
    sed -i "s,url=.*$,url=jdbc:postgresql:\/\/${YIDU_DB_HOST}:${YIDU_DB_PORT}\/${YIDU_DB_NAME},g" ${jdbc}
    sed -i "s,username=.*$,username=${YIDU_DB_USER},g" ${jdbc}
    sed -i "s,password=.*$,password=${YIDU_DB_PWD},g" ${jdbc}


    echo '-----'
    cat ${jdbc} | head -n 6
    echo '-----'

    # ------------------------------------------------------------------------------------------------------
    # ------------------------------------------------------------------------------------------------------
    echo ${SPIDER_RULE}
    # ------------------------------------------------------------------------------------------------------
    # update collect.ini
    # ------------------------------------------------------------------------------------------------------
    
    collect=${SPIDER_HOME}/collect.ini

    sed -i "s,rule_name=.*$,rule_name=${SPIDER_RULE}.xml,g" ${collect}
    

    # ------------------------------------------------------------------------------------------------------
    # update site.ini
    # ------------------------------------------------------------------------------------------------------
    
    site=${SPIDER_HOME}/site.ini

    sed -i "s,local_site_url=.*$,local_site_url=${SPIDER_SITE_HOST},g" ${site}
    sed -i "s,local_site_name=.*$,local_site_name=${SPIDER_SITE_NAME},g" ${site}
    sed -i "s,txt_file=.*$,txt_file=${SPIDER_WWW}/${SPIDER_TXT},g" ${site}
    sed -i "s,cover_dir=.*$,cover_dir=${SPIDER_WWW}/${SPIDER_COVER},g" ${site}
    

    # ------------------------------------------------------------------------------------------------------
    # update run.ini
    # ------------------------------------------------------------------------------------------------------
    
    run=${SPIDER_HOME}/run.ini
    touch ${run} && echo -rule ${SPIDER_RULE}.xml ${SPIDER_RUN} > ${run}
    
    cat ${run}

    # ------------------------------------------------------------------------------------------------------
    # ------------------------------------------------------------------------------------------------------
    echo -e "${Green}----[Config][Success]----------------------------------------------------------------------------------${Clear}"