#!/bin/sh
./delete-all-buckets.sh
./create-queue.sh
sleep 10
oc new-app -e POSTGRESQL_USER=xfer -e POSTGRESQL_PASSWORD=xfer -e POSTGRESQL_DATABASE=xferdb postgresql
sleep 3
oc new-app -f keycloak-template.yaml -p KEYCLOAK_ADMIN=admin -p KEYCLOAK_ADMIN_PASSWORD=admin -p NAMESPACE=keycloak
until oc get pods | grep keycloak | grep -wv -e deploy | grep -m 1 "Running"
do
  sleep 2
done
keycloak_pod=$(oc get pods | grep keycloak | grep -wv -e deploy | awk '{print $1}')
keycloak_route=$(oc get route keycloak --output jsonpath={.spec.host})
cat keycloak-customize.sh | oc exec -i $keycloak_pod -- sh -c "cat > /opt/keycloak/data/keycloak-customize.sh; chmod a+x /opt/keycloak/data/keycloak-customize.sh"
until curl https://$keycloak_route -sf -o /dev/null
do
  echo ">>> Waiting for the keycloak service to become ready"
  sleep 5
done
echo ">>> The keycloak service is ready"
sleep 5
oc exec -i $keycloak_pod -- sh -c "/opt/keycloak/data/keycloak-customize.sh $keycloak_route"
mvn -DskipTests -Dquarkus.kubernetes.deploy=true clean install

