#!/bin/sh
xfer_file_pod=$(oc get pods | grep xfer-file | grep -wv -e build -e deploy | awk '{print $1}')
cat xfer-model/src/main/resources/xml/money-transfers.xml | oc exec -i $xfer_file_pod -- sh -c "cat > /tmp/input/money-transfers.xml"