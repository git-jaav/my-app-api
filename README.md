# my-app-api
API TEST DEMO 

## build component with maven:

mvn clean install

## build container and tag it  (get the [id_image]):

docker build -f Dockerfile .
docker tag [id_image] api-demo

## Run with docker (Example values) :

docker run -d -t  --network host -w "/workspace/logs-api"  \\
-e ADMINBOOT_CLIENT_URL="http://localhost:9999" \
-e ADMINBOOT_CLIENT_USER="admin" \\
-e ADMINBOOT_CLIENT_PASSWORD="12345" \\
-e ADMINBOOT_CLIENT_INSTANCE_HOST="http://localhost:8089" \\
-e PORT="8089" \\
-e BUILD_PROFILE="prod_ab" \\
 [id_image]  \\
--net=host -p 8087:8089

## follow on logging:
docker logs --follow [id_container]
