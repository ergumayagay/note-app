# note-app

System Requirement

 - JDK22 https://adoptium.net/temurin/releases/?version=22
 - maven https://maven.apache.org
 - Docker - https://www.docker.com

Building the project

1. Build jar file
```
mvn clean install
```
3. Build docker image
```
docker build -t note .
```
Running the application

1. Run using docker -
```
docker run -p 8080:8080 note:latest
```
2. Open swagger-ui endpoint at localhost:8080/
