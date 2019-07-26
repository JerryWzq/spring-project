#!/bin/bash
JAVA_VERSION=`java -version 2>&1 |awk 'NR==1{ gsub(/"/,""); print $3 }'`
if  [[ $JAVA_VERSION != 1.8* ]]  ;then
 echo '当前程序只支持jdk1.8,或者指定jdk 1.8路径(java_cmd)';
fi

java_cmd=java

export HOME=$(cd `dirname $0`/../; pwd)

APP_HOME=/app/systems/project

deploy_file_name=spring-boot-druid.jar

nohup $java_cmd  \
          -Xms2G \
          -Xmx4G \
          -XX:MaxNewSize=2G \
          -Xdebug \
          -XX:+UseConcMarkSweepGC \
          -XX:+HeapDumpOnOutOfMemoryError  -jar \
          -XX:+PrintGCDetails \
          -XX:+UseConcMarkSweepGC \
          -verbose:gc -Xloggc:logs/gc.log \
          -Dspring.config.location=$APP_HOME/config/application.properties \
          -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9999 \
          --logging.config=$APP_HOME/config/logback.xml \
          $HOME/apps/$deploy_file_name   > $HOME/server.out 2>&1 &

echo Start Success!