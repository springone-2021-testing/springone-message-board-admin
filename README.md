# SpringOne Message Board Admin

This application demonstrates Spring Cloud Contract consumer testing.

To use this project, first install the necessary stub jars to your local maven repository. Run the following commands and choose `springone-message-board-service/springone-message-board-admin` at the prompt.
```shell
git clone git@github.com:springone-2021-testing/api-contracts.git
cd api-contracts
source bin/local-install_message-board-contracts.sh
```

Then, run the tests for this application:
```shell
git clone git@github.com:springone-2021-testing/springone-message-board-admin.git
cd springone-message-board-admin
./mvnw clean install
```
