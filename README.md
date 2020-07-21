# Marshmallow Java Backend Test

This application is a Java based web service that navigates an imaginary robotic cleaner through an oil spill in the sea. It is an api that takes the parameters `areaSize`, `startingPosition`, `oilPatches` and `navigationInstructions`. These parameters are used to determine how many oil patches the boat clears up and its final position.

## Setup
> Note: This setup guide is written for MacOS (as per the README).

This application is a Spring Boot Java application, built using Maven. To run it, you'll need the following:
  - Java 9 or later (some new features of List are used)
  - Maven 3.x

The latest version of Java can be downloaded from [Oracle](https://jdk.java.net/14/), or installed using brew:
  ```
  brew cask install java
  ```

Maven can be downloaded from [Apache](https://maven.apache.org/download.cgi) or installed using brew:
  ```
  brew install maven
  ```

## Building, Testing and Running

- Once these prerequisites have been installed, the application can be built either using your favourite IDE or with `mvn clean install`.
- To run tests, use the command `mvn test`.
- To run the application, use `mvn spring-boot:run`.

## Sending a POST request

To use the application, a POST request can be sent via curl or postman to `http://localhost:8080/cleaner`. The payload should be valid JSON in the following format:

```JSON
{
  "areaSize" : [5, 5],
  "startingPosition" : [1, 2],
  "oilPatches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "navigationInstructions" : "NNESEESWNWW"
}
```

This request will return the following response:

```JSON
{
    "finalPosition": [
        1,
        3
    ],
    "oilPatchesCleaned": 1
}
```

If an invalid request is sent, an error will be returned, for example the following request:

```JSON
{
  "areaSize" : [5, 5],
  "startingPosition" : [1, 2],
  "oilPatches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "navigationInstructions" : "error test"
}
```

results in the following response:

```JSON
{
    "error": "Bad Request",
    "message": "Invalid navigation instructions - must only contain cardinal directions (N,S,E,W)",
    "timestamp": "2020-07-20T18:51:21.152+00:00",
    "status": "400"
}
```
