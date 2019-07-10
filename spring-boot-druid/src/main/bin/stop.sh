#!/bin/sh

APP_NAME=ts-mock-pro

tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`

if [ ${tpid} ]; then
  echo 'Stop process...'
  kill -15 $tpid
fi

sleep 5

tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
 echo 'Stop process!'
 kill -9 $tpid
else
 echo 'Stop Success!'
fi
