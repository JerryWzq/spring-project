#!/bin/sh

APP_NAME=ts-mock
APP_HOME=/app/systems/ts-mock
JAR_NAME=ts-mock-pro.jar
cd $APP_HOME

rm -f tpid

nohup $JAVA_HOME/bin/java -Xms2G -Xmx2G -XX:MaxNewSize=1G -XX:-OmitStackTraceInFastThrow -Dspring.config.location=$APP_HOME/config/application.properties -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+HeapDumpOnOutOfMemoryError -verbose:gc -Xloggc:logs/gc.log -jar $APP_HOME/apps/$JAR_NAME --logging.config=$APP_HOME/config/logback.xml >$APP_HOME/server.out 2>&1 &

echo $! > tpid

echo Start Success!
