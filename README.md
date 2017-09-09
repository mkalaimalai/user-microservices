# Spring Boot User Micro Services

Simple implementation of Micro Services to manage Users using Spring Boot

## Build

> `mvn clean install`  - command to build the project


## Run the App as Single Actor System

> `mvn spring-boot:run` - command to run the app

## Run the Swagger API

http://localhost:8080/swagger-ui.html

## Steps to create a user

``` curl -X POST  http://localhost:8080//api/user  -H 'accept: application/json'  -H 'cache-control: no-cache'  -H 'content-type: application/json'  -H 'postman-token: e07adf15-dabb-3384-0092-4dc926974b34'  -d '{ "user_name": "Bret", "email" : "Sincere@april.biz", "password": "welcome@123", "first_name": "Leanne", "last_name": "Graham", "middle_name": "N", "addresses": [ { "line1": "Kulas Light", "line2": "Apt. 556", "city": "Gwenborough", "zip": "92998-3874", "country": "US", "type": "HOME" } ] }'  ```


