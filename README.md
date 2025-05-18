# blue_oasis_api

blue_oasis_api: Api developed in Spring Boot in order to create reservations, check in, check out for the Blue Oasis Hotel

### Consola:

### Normal sin perfiles

- mvn clean
- mvn install

### Normal con perfiles y encriptando claves

- mvn clean

### Para desarrollo

- mvn jasypt:encrypt "-Djasypt.encryptor.password=[aqui va la clave]" "-Djasypt.plugin.path=file:src/main/resources/application-dev.properties"

- mvn -DskipTests clean install -Pdev

- java -jar "-Djasypt.encryptor.password=[aqui va la clave]" "-Djasypt.plugin.path=file:src/main/resources/application-dev.properties" target/blue_oasis-0.0.1-SNAPSHOT.jar

### Para QA

- mvn jasypt:encrypt "-Djasypt.encryptor.password=[aqui va la clave]" "-Djasypt.plugin.path=file:src/main/resources/application-stg.properties"

- mvn -DskipTests clean install -Pstg

- java -jar "-Djasypt.encryptor.password=[aqui va la clave]" "-Djasypt.plugin.path=file:src/main/resources/application-stg.properties" target/blue_oasis-0.0.1-SNAPSHOT.jar

### Para Produccion

- mvn jasypt:encrypt "-Djasypt.encryptor.password=[aqui va la clave]" "-Djasypt.plugin.path=file:src/main/resources/application-prod.properties"

- mvn -DskipTests clean install -Pprod

- java -jar "-Djasypt.encryptor.password=[aqui va la clave]" "-Djasypt.plugin.path=file:src/main/resources/application-prod.properties" target/blue_oasis-0.0.1-SNAPSHOT.jar
