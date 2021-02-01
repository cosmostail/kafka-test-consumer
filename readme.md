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

## get glue schema
``` 
aws glue get-schema --schema-id SchemaName=person-test-schema,RegistryName=scott-test
aws glue get-schema-version --schema-id SchemaName=person-test-schema,RegistryName=scott-test --schema-version-number LatestVersion=true
```