package publisher;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProducerVideoMessages {
/*
* Kafka Producer to send Images to the Kafka.
* */
    public static void sendImages(byte[] imageArray, String topicName) throws IOException {
        String key = "Key1";
        Properties props = new Properties(); //Mandatory properties
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");

        KafkaProducer<String, byte[]> producer = new KafkaProducer<String, byte[]>(props);
        System.out.println("Testing from producer end : "+imageArray);
        producer.send(new ProducerRecord<String, byte[]>(topicName, key, imageArray));
        producer.flush();
        producer.close();
    }

    public static NewTopic createTopics(String topicName) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(new File("C:\\Users\\Dell\\Downloads\\kafka_2.12-2.3.0\\kafkaproperty\\kafka.properties")));
        AdminClient adminClient = AdminClient.create(properties);
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1); //new NewTopic(topicName, numPartitions, replicationFactor)

        List<NewTopic> newTopics = new ArrayList<NewTopic>();
        newTopics.add(newTopic);
        adminClient.createTopics(newTopics);
        adminClient.close();
        return newTopic;
    }
}
