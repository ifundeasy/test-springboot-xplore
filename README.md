# test-springboot-xplore
## Description
This project is an implementation of a "News" pipeline consisting of five Spring Boot applications: `producer-api`, `categorizer-service`, `collector-service`, `publisher-api` and `news-client`.

## Prerequisites
1. java 17+
2. docker

On my laptop, I'm using `jabba` for easy switch `Java` version. Because this project need `Java 17+`, and so
```sh
jabba install openjdk@1.17.0
jabba use openjdk@1.17.0
java --version
```
Output
```output
openjdk 17 2021-09-14
OpenJDK Runtime Environment (build 17+35-2724)
OpenJDK 64-Bit Server VM (build 17+35-2724, mixed mode, sharing)
```

## Service dependencies
1. elasticsearch 7.17.6
2. zookeeper 7.0.1
3. kafka 7.0.1
4. kafka-rest-proxy 7.0.1
5. kafka-topics-ui 0.9.4
6. kafka-manager 3.0.0.5
7. zipkin 2.23.19

You can install these manually or using command
```sh
docker-compose up -d
```

To stop and remove these service dependencies
```sh
docker-compose down -v
```

## Application
**producer-api**
> http://localhost:9080/swagger-ui.html

Spring Boot Web Java application that creates news and pushes news events to producer.news topic in Kafka.
<br/><br/>
**categorizer-service**

Spring Boot Web Java application that listens to news events in producer.news topic in Kafka, categorizes and pushes them to categorizer.news topic.
<br/><br/>
**collector-service**

Spring Boot Web Java application that listens for news events in categorizer.news topic in Kafka, saves them in Elasticsearch and pushes the news events to collector.news topic.
<br/><br/>
**publisher-api**
> http://localhost:9083/swagger-ui.html

Spring Boot Web Java application that reads directly from Elasticsearch and exposes a REST API. It doesn’t listen from Kafka.
<br/><br/>
**news-client**
> http://localhost:8080

Spring Boot Web java application that provides a User Interface to see the news. It implements a Websocket that consumes news events from the topic collector.news. So, news are updated on the fly on the main page. Besides, `news-client` communicates directly with `publisher-api` whenever search for a specific news or news update are needed. 
The Websocket operation is shown in the short gif below. News is created in `producer-api` and, immediately, it is shown in `news-client`.

## Commands
### Generate new events
```sh
./mvnw clean install --projects commons-news
```

### Run with maven
Installing dependencies
```sh
# make sure your terminal env runs with java 17 or above
./mvnw clean install
```

**eureka-server**
```sh
./mvnw clean spring-boot:run --projects eureka-server
```

**producer-api**
```sh
./mvnw clean spring-boot:run --projects producer-api -Dspring-boot.run.jvmArguments="-Dserver.port=9080"
```

**categorizer-service**
```sh
./mvnw clean spring-boot:run --projects categorizer-service -Dspring-boot.run.jvmArguments="-Dserver.port=9081"
```

**collector-service**
```sh
./mvnw clean spring-boot:run --projects collector-service -Dspring-boot.run.jvmArguments="-Dserver.port=9082"
```

**publisher-api**
```sh
./mvnw clean spring-boot:run --projects publisher-api -Dspring-boot.run.jvmArguments="-Dserver.port=9083"
```

**news-client**
```sh
./mvnw clean spring-boot:run --projects news-client
```

### Run with docker
**Build images**
```sh
./docker-build.sh
```

**Run container**
```sh
./start-apps.sh
```

**Stop container**
```sh
./stop-apps.sh
```