    #!/bin/bash

    Green='\033[0;32m'
    Clear='\033[0m'

    echo -e "${Green}----[Config]----------------------------------------------------------------------------------${Clear}"
    
    cd $YIDU_HOME

    # ------------------------------------------------------------------------------------------------------
    # copy www
    # ------------------------------------------------------------------------------------------------------


    dir=$CATALINA_HOME/webapps/ROOT
    files=`ls $dir`
    if [ -z "$files" ]; then
        echo "Folder 'ROOT' copying ..."
        mv $YIDU_HOME/ROOT/* $dir/ 
    else
        echo "Folder $dir is not empty."
    fi

    # ------------------------------------------------------------------------------------------------------
    # update jdbc properties
    # ------------------------------------------------------------------------------------------------------

    mkdir -p WEB-INF/classes
    jdbc=WEB-INF/classes/jdbc.properties
    cp conf/jdbc.properties ${jdbc}

    sed -i "s,driverClassName=*,driverClassName=org.postgresql.Driver,g" ${jdbc}
    sed -i "s,url=*,url=jdbc:postgresql:\/\/${YIDU_DB_HOST}:${YIDU_DB_PORT}\/${YIDU_DB_NAME},g" ${jdbc}
    sed -i "s,username=*,username=${YIDU_DB_USER},g" ${jdbc}
    sed -i "s,password=*,password=${YIDU_DB_PWD},g" ${jdbc}


    echo '-----'
    echo ${jdbc}

    cat ${jdbc}
    echo '-----'

    ls -l $CATALINA_HOME/webapps/ROOT

    # Not working: jar tool is part of jdk
    # jar -uvf $CATALINA_HOME/webapps/ROOT.war WEB-INF/classes/jdbc.properties

    # does not deploy (unpack) ROOT.war
    # ls $CATALINA_HOME/webapps/ROOT
    
    cp ${jdbc} $CATALINA_HOME/webapps/ROOT/WEB-INF/classes/jdbc.properties
    
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