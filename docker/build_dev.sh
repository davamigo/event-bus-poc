#!/bin/bash

dockerfile="docker-compose-dev.yml"
dir=$(dirname $0)
cd $dir

echo -e "Building $dockerfile..."

docker-compose -f $dockerfile build --pull --force --no-cache
