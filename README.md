# Spring Boot 3.0, 3.1, 3.2, 3.3 újdonságai

Spring Boot 3.0
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Release-Notes

* Java 17
* Jakarta EE -> `javax` helyett `jakarta` csomag
* Problem Details
* Tracing
* Native image
* Az új Git plugin
* Actuator - `HttpTrace` helyett `HttpExchange`

További problémák:

* Swagger UI

Spring Boot 3.1
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1-Release-Notes

* Testcontainers
* Docker compose
* Spring Security Authorization Server Spring Boot Starter
    * OAuth 2.0 Identification Provider (IDP)

Spring Boot 3.2
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.2-Release-Notes

* RestClient Support
* Logged Application Name
* Logging Correlation IDs
* Java 21 Virtual Threads support
* Initial support for JVM Checkpoint Restore (Project CRaC)
* SSL Bundle Reloading
* You can now use Micrometer’s @Timed, @Counted, @NewSpan, @ContinueSpan and @Observed annotations. The aspects for them are now auto-configured if you have AspectJ on the classpath.
* A Spring Boot 3.2 verzióban változott a `JarLauncher` csomagja, helyesen
  `org.springframework.boot.loader.launch.JarLauncher`.

Spring Boot 3.3 (M2)
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.3-Release-Notes

* `JwtAuthenticationConverter`

### Alkalmazás indítása

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432 --name employees-postgres postgres
```

```
docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin
```

## Spring Boot 3.0

* Java 17
* Jakarta EE -> `javax` helyett `jakarta` csomag


### Problem Details

`EmployeesExceptionHandler`

`application.properties`

```properties
spring.mvc.problemdetails.enabled = true
```

### Tracing

http://localhost:9411

```java
@Observed(name = "list.employees", contextualName = "list.employees", lowCardinalityKeyValues = {"framework", "spring"})
```

* `pom.xml`: `spring-boot-starter-aop`, `net.ttddyy.observation:datasource-micrometer-spring-boot`

### Native image

```shell
mvnw spring-boot:build-image
mvnw -Pnative spring-boot:build-image
```

### Információk megjelenítése

Az új Git plugin:

```xml
<plugin>
    <groupId>io.github.git-commit-id</groupId>
    <artifactId>git-commit-id-maven-plugin</artifactId>
</plugin>
```

```http
### Info actuator
GET http://localhost:8080/actuator/info
```

### Actuator - HttpTrace helyett HttpExchange

A `HttpTraceRepository` helyett `HttpExchangeRepository`, 
az `InMemoryHttpTraceRepository` helyett `InMemoryHttpExchangeRepository`,
és a `/actuator/httptrace` helyett `/actuator/httpexchanges` URL használható.

```java
@Bean
public HttpExchangeRepository httpExchangeRepository() {
    return new InMemoryHttpExchangeRepository();
}
```

```http
### Http Exchange
GET http://localhost:8080/actuator/httpexchanges
```

### Swagger UI

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.1.0</version>
</dependency>
```

http://localhost:8080/swagger-ui.html

## Spring Boot 3.1

### Testcontainers

* `EmployeesApplicationIT`
* `EmployeesTestApplication`

### Docker compose

```xml
<dependency>
    <groupId>net.ttddyy.observation</groupId>
    <artifactId>datasource-micrometer-spring-boot</artifactId>
    <version>1.0.2</version>
</dependency>
```

## Spring Boot 3.2

## RestClient support

`employees-sb3-client`

* Reactive
* Lombok
* Actuator
* Zipkin

### Logging

```plain
2024-02-21T13:21:13.784+01:00  INFO 24120 --- [employees] [nio-8080-exec-1] [65d5eab9c4c73ec25f5fa1e23817a27a-2db1c92a9ca868f9] employees.EmployeesService               : List employees
```

