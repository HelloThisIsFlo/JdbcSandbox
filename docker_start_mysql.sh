#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"


docker run \
    --name jdbc-sandbox-db \
    -e MYSQL_ROOT_PASSWORD=rootpass \
    -e MYSQL_DATABASE=jdbc-sandbox \
    -e MYSQL_USER=florian \
    -e MYSQL_PASSWORD=pass \
    -p 3306:3306 \
    -p 8080:8080 \
    -d \
    mysql:latest
