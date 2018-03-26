#!/bin/bash

dockerfile="docker-compose-prod.yml"
dir=$(dirname $0)
cd $dir

cd ../FCProxyService
mvn package

cd ../UserService
mvn package

cd ../BasketService
mvn package

echo -e "Building $dockerfile..."

cd ../docker
docker-compose -f $dockerfile build --pull --force --no-cache
