# Eventfinder
Проект по поиску бесплатных событий. #пока_что #Spring

### Как запустить:

Понадобится БД, я использую postgresql и тебе советую. Впрочем, это не так важно, ведь структуру БД создает JPA, подойдет любая другая. У меня стоял mysql, но он не может в кириллицу без изъебств.  
Поэтому я запускаю базу в докере: 

```
docker run --name psql -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres
docker container ls
# get pid and use it
# and then you can just 
docker container start 1234567890ab
docker exec -it 1234567890ab bash
# to connect to bash shell
psql -U postgres
```

Создаю базу.
После этого добавляешь в application.properties примерно следующее:

```
spring.jpa.hibernate.ddl-auto=create

spring.datasource.url=jdbc:postgresql://localhost:5432/eventfinder
spring.datasource.username=postgres
spring.datasource.password=password
```

Надо бы разобраться с тем, как JPA мог бы изменять схему БД в соответствиями с изменениями в коде, но он у меня валится, хз.

И запускаешь. Не забудь собрать maven dependencies.

### Как выкатывать:

TODO
