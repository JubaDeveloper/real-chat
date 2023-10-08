![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![GraphQL](https://img.shields.io/badge/-GraphQL-E10098?style=for-the-badge&logo=graphql&logoColor=white)
![Socket.io](https://img.shields.io/badge/Socket.io-black?style=for-the-badge&logo=socket.io&badgeColor=010101)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

# Real Chat

Chat to conversation in real time using Socket.io and other technologies

## Motivations

#### That will be developed to improve knowledge in:

- `SpringBoot`
- `GraphQL`
- `Socket.io (Java)`
- `Hibernate (JPA)`
- `Authentication`

## Configuring/Running application

### Setting up app configurations 

- `application.xml`:
```yaml
spring:
  graphql:
    graphiql:
      enabled: true
  devtools:
    restart:
      enabled: true
    livereload:
      enabled: false
  datasource:
    url: DB_URL
    username: USER # Test env
    password: PASSWORD # Test env
    driver-class-name=DRIVER_CLASS
  jooq:
    sql-dialect: SQL_DIALECT
  jpa:
    hibernate:
      ddl-auto: "create-drop"
    show-sql: true
provider: "org.hibernate.jpa.HibernatePersistenceProvider"
server:
  port: 3000
properties:
  private_key: PRIVATE_KEY
```

### Starting application

- `docker-compose`
````bash
docker compose up -d 
````
- `maven directly`
````bash
mvn spring-boot:run
````
- `JAR version`
```bash
java -Xms512m -Xmx1024m -jar target/$JAR_FILE
```

### Building app

- `maven`
```bash
mvn clean package
```