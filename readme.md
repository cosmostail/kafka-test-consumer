## run
``` 
./gradlew run
```

## get latest schema 
``` 
curl -X GET "http://localhost:8081/subjects/person-test-value/versions/latest/schema" -o app/src/main/resources/avro/KafkaTest/Person.avsc
```

## build schema class
``` 
java -jar ~/code/avro/avro-tools-1.10.1.jar compile schema app/src/main/resources/avro/KafkaTest/Person.avsc app/src/main/java/
```