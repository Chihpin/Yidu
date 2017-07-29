#!/bin/bash

#export JAVA_HOME=/usr/java/jdk1.6.0_45
#export PATH=$PATH:$JAVA_HOME/bin
#export CLASSPATH=.:$JAVA_HOME/jre/lib:$JAVA_HOME/lib:$JAVA_HOME/lib/tools.jar

#/usr/java/jdk1.6.0_45/bin/java -jar spider.jar -ca &

cd $SPIDER_HOME
./scripts/spider_config.sh

#
# java -jar spider.jar -ca &

# 多线程

java -jar spider.jar -m &