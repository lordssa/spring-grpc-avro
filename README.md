# User Administration

API Rest and GRPC

Java Spring Boot using Clean Architecture and Embedded DataBase.

### Prerequisites

* [Java 11](https://www.java.com/pt_BR/download/) - Development Kit
* [Git](https://git-scm.com/downloads) - Open source distributed version control system
* [IntelliJ](https://www.jetbrains.com/idea/) - Powerful source code editor (Not mandatory)

### Installing

Checkout the code from Github repository
```
$ git clone https://github.com/lordssa/spring-grpc-avro.git
```

Once you have maven installed on your environment, install the project dependencies via:

```
mvn clean install
```

To run unit tests, you can also execute:
```
mvn clean test
```

## Running

Once you have installed dependencies, this can be run from the `GrpcSpringBootExampleApplication.java` main method directly,
or from a command line:
```
mvn spring-boot:run
```

The API will be available at http://localhost:8080/ exposing two resources:

Creating User

```
curl -X POST \
  http://localhost:8080/user \
  -H 'Content-Type: application/json' \  
  -d '{
    "first_name": "AAAAAA",
    "last_name": "AAAAAAAA",
    "email": "user@email.com",
    "password": "******",
    "addresses": [
        {
            "first_address": "AAAAAAAAAAA, 657",
            "second_address": "AAAAAAAAAAA, 435",
            "city": "city name",
            "state": "state",
            "zip": "zip code",
            "country": "country name"
        }
    ]
}'
```

Finding by Country
```
curl -X GET \
  http://localhost:8080/user/country/USA \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Spring Boot 2
* [Maven](https://maven.apache.org/) - Dependency Management
* [Derby](https://db.apache.org/) - Open source relational database implemented entirely in Java 
* [Grpc](https://grpc.io) - Remote Procedure Call (RPC) framework
* [Avro](https://avro.apache.org) - Data serialization system

## Modules

|**Name**                | **Description**                   |
| ---------------------- | --------------------------------- |
| admin-person-service   | API and Services source           |
| interface              | Generate shared stubs from Avro   |
| tests                  | Integration tests                 |

## Authors

* **Cid Soares** - *Initial work* 