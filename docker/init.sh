#!/bin/bash

dir=$(dirname $0)
cd $dir

docker exec -it event_bus_user_database mysql -uroot -proot -D user_service \
    -e "CREATE TABLE IF NOT EXISTS user_service.user (id INT NOT NULL, global_id INT NOT NULL DEFAULT 0, first_name VARCHAR(48) NOT NULL, last_name VARCHAR(72) NOT NULL, address VARCHAR(128) NULL DEFAULT NULL, PRIMARY KEY (id));"


