#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"


docker kill jdbc-sandbox-db
docker rm jdbc-sandbox-db
