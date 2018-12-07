package helloworld.partial;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyHelloProducer {

    public void createProducer() {
        long numberOfEvents = 5;

        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.101:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        //Create a Kafka Producer

        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < numberOfEvents; i++) {
            String key = "firstkey";
            String value = "firstValue" + i;

            //Create the Producer Record

            ProducerRecord<String, String> record = new ProducerRecord<>("topic1", key, value);

            //Write the record

            try {
                producer.initTransactions();
                producer.send(record, ((recordMetadata, e) -> {
                    if (e != null)
                        e.printStackTrace();
                    else
                        System.out.println("Message string = " + record.value() + ", Offset = " + recordMetadata.offset());
                }));
                producer.commitTransaction();
            } catch (ProducerFencedException e) {
                producer.close();
            } catch (KafkaException e) {
                producer.abortTransaction();
            }

            //Print it

            System.out.printf("key = %s, value = %s\n", key, value);
        }

        //Be sure that the messages has been sent
        producer.flush();

        producer.close();
    }

    public static void main(String[] args) {
        MyHelloProducer myHelloProducer = new MyHelloProducer();
        myHelloProducer.createProducer();
    }
}
