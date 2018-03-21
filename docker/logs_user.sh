#!/bin/bash

service="event_bus_user_service";

enabled=$( docker ps --format "{{.Names}}" | grep -i "$service" )
if [ "$enabled" == "" ]
then
    echo -e "\033[31mContainer \033[33m$service\033[31m not started!\033[0m\n";
    exit 1;
fi;

dir=$(dirname $0)
cd $dir

docker logs $service
