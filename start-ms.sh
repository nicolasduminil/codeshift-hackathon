#!/bin/sh
./delete-all-buckets.sh
./create-queue.sh
sleep 10
oc new-app -e POSTGRESQL_USER=xfer -e POSTGRESQL_PASSWORD=xfer -e POSTGRESQL_DATABASE=xferdb postgresql
mvn -DskipTests -Dquarkus.kubernetes.deploy=true clean install
sleep 3
#./copy-xml-file.sh