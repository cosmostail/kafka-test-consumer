/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package KafkaTestConsumer;

import KafkaTest.Person;
import com.amazonaws.services.schemaregistry.deserializers.avro.AWSKafkaAvroDeserializer;
import com.amazonaws.services.schemaregistry.utils.AWSSchemaRegistryConstants;
import com.amazonaws.services.schemaregistry.utils.AvroRecordType;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class App {
    public String getGreeting() {
        return "Hello Consumer!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AWSKafkaAvroDeserializer.class.getName());
        props.put(AWSSchemaRegistryConstants.AWS_REGION, "us-east-1"); // Pass an AWS Region
        props.put(AWSSchemaRegistryConstants.AVRO_RECORD_TYPE, AvroRecordType.SPECIFIC_RECORD.getName());
        props.put(AWSSchemaRegistryConstants.CACHE_TIME_TO_LIVE_MILLIS, "86400000"); // If not passed, uses 86400000
        props.put(AWSSchemaRegistryConstants.CACHE_SIZE, "10"); // default value is 200

        KafkaConsumer<String, Person> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("person-test-glue"));

        while (true) {
            final ConsumerRecords<String, Person> records = consumer.poll(Duration.ofMillis(100));
            for (final ConsumerRecord<String, Person> record : records) {
                final String key = record.key();
                final Person value = record.value();
                System.out.println("key=" + key + ", value=" + value);
            }
        }
    }
}
