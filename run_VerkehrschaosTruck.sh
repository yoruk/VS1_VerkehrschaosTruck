#!/bin/bash

if [ -f VerkehrschaosTruck.jar ]; then
        rm VerkehrschaosTruck.jar
        echo "alte VerkehrschaosTruck.jar geloescht"
fi

cd bin
jar -cmf ../src/META-INF/MANIFEST.MF ../VerkehrschaosTruck.jar verkehrschaos/*.class verkehrschaostruck/*.class
cd ..

java -jar VerkehrschaosTruck.jar 20000 localhost Truckie TruckGmbH

