# GENZ

## Overview

The project implements the logic for a sport prediction game.

## Prerequisites
- JDK 1.8 (Oracle)
- Maven 3
- MySQL

## Installation 

```
mvn clean install -DskipTests
```

## Deployment
```
mvn spring-boot:run
```

## Url to interact with Swagger
```
http://localhost:8086/swagger-ui.html
```

## The format of the User model should be:

```json
{
    "id": 1,
    "score": 0,
    "email": "nik@nik.com",
    "password": "nikNewPass",
    "name": "nik",
    "surname": "nikos",
    "age": 19,
    "phoneNumber": "0309849",
    "username": "nikUser"
  }
```
