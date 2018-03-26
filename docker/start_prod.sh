#!/bin/bash

dockerfile="docker-compose-prod.yml"
dir=$(dirname $0)
cd $dir

docker-compose -f $dockerfile build
if [ $? -ne 0 ]; then
    exit 1
fi

docker-compose -f $dockerfile up -d --remove-orphans
if [ $? -ne 0 ]; then
    exit 1
fi
