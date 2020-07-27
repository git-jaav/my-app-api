# my-app-api
API TEST DEMO 

## build component with maven:

mvn clean install

## build container and tag it  (get the [id_image]):

docker build -f Dockerfile .
docker tag [id_image] api-demo

## build container and set a repository name
docker build -f Dockerfile .  -t [repository_name]

## Run with docker (Example values) NETWORK HOST MODE (Taking port from propertie file or env. variable) :

docker run -d -t  --network host -w "/workspace/logs-api"  \
-e ADMINBOOT_CLIENT_URL="http://localhost:9999" \
-e ADMINBOOT_CLIENT_USER="admin" \
-e ADMINBOOT_CLIENT_PASSWORD="12345" \
-e PORT="8089" \
-e BUILD_PROFILE="prod_ab" \
 [id_image]  \
--net=host

## Run with docker (Example values)  DEFAULT BRIDGE MODE  (Parameter "-p" set port of container) :

docker run -d -t  -w "/workspace/logs-api"  \
-e ADMINBOOT_CLIENT_URL="http://localhost:9999" \
-e ADMINBOOT_CLIENT_USER="admin" \
-e ADMINBOOT_CLIENT_PASSWORD="12345" \
-e PORT="8089" \
-e BUILD_PROFILE="prod_ab" \
 [id_image]  \
--net=host -p 8087:8089


## follow on logging:
docker logs --follow [id_container]
