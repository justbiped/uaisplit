#!/bin/bash
#curl -u "roubertedgar_qTdHJK:ujsLgUC4qQfwPoPC3jBi" \
#-X POST "https://api-cloud.browserstack.com/app-automate/espresso/v2/build" \
#-d '{"app": "bs://b45976588c08d55964ec28f8ea303d356e5b03e8", "testSuite": "bs://277be667d78042cf6ac76522c17b9fae73472f3c", "devices": ["Samsung Galaxy S9 Plus-9.0"]}' \
#-H "Content-Type: application/json"
#
#
#gcloud firebase test android run \
#    --type instrumentation \
#    --app app-local.apk \
#    --test app-local-androidTest.apk \
#    --device model=Pixel2,version=28,locale=en,orientation=portrait \
#    --timeout 300s

modules=("app" "location")

for i in "${modules[@]}"
do
    ./gradlew $i:pixel2LocalAndroidTest
    sleep 5
done