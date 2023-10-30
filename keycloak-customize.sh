#!/bin/bash
KCADM=/opt/keycloak/bin/kcadm.sh
$KCADM config credentials --server https://$1/auth --realm master --user admin --password admin
$KCADM create realms -s realm=ncts -s enabled=true
$KCADM create users -r ncts -s username=pradmin -s enabled=true
$KCADM set-password -r ncts --username pradmin --new-password password1
$KCADM create users -r ncts -s username=pruser -s enabled=true
$KCADM set-password -r ncts --username pruser --new-password password1
PRCLIENT_ID=$($KCADM create clients -r ncts -s clientId=prclient -s bearerOnly="true" -s enabled=true -s clientAuthenticatorType=client-secret -s secret=thisissecret -i)
$KCADM create clients -r ncts -s clientId=curl -s publicClient="true" -s directAccessGrantsEnabled="true" -s "redirectUris=[\"http://192.168.96.12:8081\", \"http://localhost:8081\"]" -s enabled=true -s standardFlowEnabled="false"
$KCADM create clients/$PRCLIENT_ID/roles -r ncts -s name=prmanager
$KCADM create roles -r ncts -s name=prmanager
$KCADM add-roles --uusername pradmin --rolename prmanager -r ncts
$KCADM add-roles -r ncts --uusername pradmin --cclientid prclient --rolename prmanager