    #!/bin/bash

    Green='\033[0;32m'
    Clear='\033[0m'

    echo -e "${Green}----[Config]----------------------------------------------------------------------------------${Clear}"


    # ------------------------------------------------------------------------------------------------------
    # update jdbc properties
    # ------------------------------------------------------------------------------------------------------
    mkdir -p $YIDU_HOME/WEB-INF/classes
    jdbc=$YIDU_HOME/WEB-INF/classes/jdbc.properties
    cp $YIDU_HOME/conf/jdbc.properties ${jdbc}

    sed -i "s,driverClassName=*,driverClassName=org.postgresql.Driver,g" ${jdbc}
    sed -i "s,url=*,url=jdbc:postgresql:\/\/${YIDU_DB_HOST}:${YIDU_DB_PORT}\/${YIDU_DB_NAME},g" ${jdbc}
    sed -i "s,username=*,username=${YIDU_DB_USER},g" ${jdbc}
    sed -i "s,password=*,password=${YIDU_DB_PWD},g" ${jdbc}


    echo '-----'
    echo ${jdbc}

    cat ${jdbc}
    echo '-----'

    ls -l $CATALINA_HOME/webapps/

    # Not working: jar tool is part of jdk
    # jar -uvf $CATALINA_HOME/webapps/ROOT.war WEB-INF/classes/jdbc.properties

    # does not deploy (unpack) ROOT.war
    # ls $CATALINA_HOME/webapps/ROOT
    # cp ${jdbc} $CATALINA_HOME/webapps/ROOT/WEB-INF/classes/jdbc.properties
    echo $(whoami)

    zip -ur $CATALINA_HOME/webapps/ROOT.war WEB-INF/classes/jdbc.properties

    # unzip $CATALINA_HOME/webapps/ROOT.war

    # ------------------------------------------------------------------------------------------------------
    # ------------------------------------------------------------------------------------------------------
    # rm -r $YIDU_HOME/WEB-INF

    echo -e "${Green}----[Config][Success]----------------------------------------------------------------------------------${Clear}"