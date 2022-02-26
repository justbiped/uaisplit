curl -u "roubertedgar_qTdHJK:ujsLgUC4qQfwPoPC3jBi" \
-X POST "https://api-cloud.browserstack.com/app-automate/espresso/v2/build" \
-d '{"app": "bs://87547a4a008f4403316c7bc4a315dbb384811597", "testSuite": "bs://fd60d643f9aa4a601c7f9093427035a3777b9c7c", "devices": ["Samsung Galaxy S9 Plus-9.0"]}' \
-H "Content-Type: application/json"