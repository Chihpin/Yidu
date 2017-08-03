# /bin/bash

ver=1.1.9-beta
repo=czbin/yidu

#----------------------------------------------------------------
#----------------------------------------------------------------
UNDER='\033[4m'
Green='\033[0;32m'
Blue='\033[34m'
Clear='\033[0m'

root=`pwd`

function usage
{
    echo -e "${UNDER}Usage:${Clear}"
    echo ""
    echo -e "    $ ${Green}./yidu.sh COMMAND [*]${Clear}    *All (yidu , yidu-spider)"
    echo ""
    echo -e "${UNDER}Commands:${Clear}"
    echo ""
    echo -e "    ${Green}+ bootstrap${Clear}   Install plugins"
    echo -e "    ${Green}+ package${Clear}     Clean and package"
    echo -e "    ${Green}+ build${Clear}       Package and build docker image "
    echo -e "    ${Green}+ push${Clear}        Tag image and push to docker hub repository"
    echo -e "    ${Green}+ deploy${Clear}        build and push"

    echo ""
    echo -e "${UNDER}Options:${Clear}"
    echo ""
    echo -e "    ${Blue}--version${Clear}     Yidu sysytem version"
    echo -e "    ${Blue}--help${Clear}         "
    echo ""
}

function bootstrap
{
    brew install maven 
    mvn install:install-file  -Dfile=lib/IKAnalyzer-3.2.8.jar  -DgroupId=org.wltea.analyzer  -DartifactId=IKAnalyzer -Dversion=3.2.8 -Dpackaging=jar
    mvn install:install-file  -Dfile=lib/Sdk4J.jar  -DgroupId=com.qq  -DartifactId=connect -Dversion=2.0.0 -Dpackaging=jar
}

function package
{
    mvn clean && mvn package
}

function build
{
    if [ "$2" == "*" ]; then
        cd spider
        docker build . -t yidu-spider:latest
        cd ..
    fi
    mvn clean && mvn package && docker build . -t yidu:latest
}


function push
{
    if [ "$2" == "*" ]; then
        docker tag yidu-spider:latest czbin/yidu-spider:latest
    fi
    docker tag yidu:latest czbin/yidu:latest

    if [ "$2" == "*" ]; then
        docker push czbin/yidu-spider:latest
    fi
    docker push czbin/yidu:latest
}

function deploy
{
    build
    push
    
    #ssh to server, and recreate yidu container

    echo -e "\nssh to server, up to date image, container ... ...\n"
    
ssh core@chihpin.com << EOF
        cd yidu
        docker-compose stop > /dev/null 
        echo "y" | docker-compose rm
        docker pull czbin/yidu:latest
        docker-compose up -d
        exit
EOF

    echo -e "${Green}Success！${Clear}🍺 "
}

if [ "$1" == "" ]; then
    usage
    exit 1
fi


while [ "$1" != "" ]; do
    case $1 in
        bootstrap )     bootstrap
                        exit
                        ;;
        package )       package
                        exit
                        ;;
        build )         build
                        exit
                        ;;
        push )          push
                        ;;
        deploy )        deploy
                        ;;
        -v | -version ) shift
                        echo ${ver}
                        ;;
        -h | -help )    usage
                        exit
                        ;;
        * )             usage
                        echo "*"
    esac
    shift
done