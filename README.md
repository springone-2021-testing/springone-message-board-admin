# SpringOne Message Board Admin

**Reference:**
Spring Cloud Contract documentation on _Consumer Driven Contracts with Contracts in an External Repository_—[consumer flow](https://cloud.spring.io/spring-cloud-contract/reference/html/using.html#flows-cdc-contracts-external-consumer).

**Set working directory**
```shell
export PROJECT_HOME=~/workspace/springone-2021-testcontainers
mkdir -p $PROJECT_HOME
```

**Create new producer project**
- Alternatively, clone this repo
```shell
cd $PROJECT_HOME
PROJECT_DEPENDENCIES_1=web,cloud-contract-stub-runner
PROJECT_DEPENDENCIES_2=lombok,cloud-resilience4j,actuator,restdocs
PROJECT_DEPENDENCIES="${PROJECT_DEPENDENCIES_1}"
spring init \
  --groupId=springone \
  --artifactId=message-board-admin \
  --name=message-board-admin \
  --description="Demo project for SpringOne 2021" \
  --dependencies=${PROJECT_DEPENDENCIES} \
  --extract springone-message-board-admin
cd springone-message-board-admin
git init
gh repo create springone-2021-testcontainers/springone-message-board-admin --public --confirm
echo "# SpringOne Message Board Admin" > README.md
git add .
git commit -m "initial commit"
git push --set-upstream origin main
```

**Download contracts and install stubs jar locally**
```shell
cd $PROJECT_HOME
git clone git@github.com:springone-2021-testcontainers/api-contracts.git
#cd api-contracts/message-board
#mvn clean spring-cloud-contract:convert spring-cloud-contract:generateStubs
#mvn install:install-file -Dfile=target/message-board-contracts-0.0.1-SNAPSHOT-stubs.jar -DpomFile=pom.xml
#tree ~/.m2/repository/springone/contracts/message-board-contracts/

mkdir -p $PROJECT_HOME/springone-message-board-admin/src/main/resources/contracts/springone-message-board-service/springone-message-board-admin
cp -R $PROJECT_HOME/api-contracts/message-board/src/test/resources/contracts/springone-message-board-admin/* $PROJECT_HOME/springone-message-board-admin/src/main/resources/contracts/springone-message-board-service/springone-message-board-admin
cp -R $PROJECT_HOME/api-contracts/message-board/src/test/resources/contracts/springone-message-board-admin/* $PROJECT_HOME/springone-message-board-admin/src/main/resources/contracts/springone-message-board-service/springone-message-board-admin
tree $PROJECT_HOME/springone-message-board-admin/src/main/resources/contracts
```

**Install the producer stubs to local storage**
```shell
cd $PROJECT_HOME/springone-message-board-admin
./mvnw clean install
```