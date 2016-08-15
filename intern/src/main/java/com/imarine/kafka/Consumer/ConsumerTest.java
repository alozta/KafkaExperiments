/**
 * Created by alozta on 8/15/16.
 *
 * Message consumer for Kafka.
 */

package com.imarine.kafka.Consumer;

import java.util.*;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerTest {
    private ConsumerConnector consumerConnector = null;
    private final String topic = "notification";        //partition name

    //SET PROPERTIES
    public void initialize() {
        Properties props = new Properties();
        props.put("zookeeper.connect", "127.0.0.1:2181");   //default zookkeper ip: 127.0.0.1:2181
        props.put("group.id", "testgroup");
        props.put("zookeeper.session.timeout.ms", "6000");  //consumer did not work  if timeout is set to default 400, but it worked in 6000
        props.put("zookeeper.sync.time.ms", "300");
        props.put("auto.commit.interval.ms", "1000");
        ConsumerConfig conConfig = new ConsumerConfig(props);
        consumerConnector = Consumer.createJavaConsumerConnector(conConfig);
    }

    public void consume() {
        //Key = topic name, Value = No. of threads for topic
        Map<String, Integer> topicCount = new HashMap<String, Integer>();
        topicCount.put(topic, new Integer(1));

        //ConsumerConnector creates the message stream for each topic
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams =
                consumerConnector.createMessageStreams(topicCount);

        // Get Kafka stream for topic 'mytopic'
        List<KafkaStream<byte[], byte[]>> kStreamList =
                consumerStreams.get(topic);
        // Iterate stream using ConsumerIterator
        for (final KafkaStream<byte[], byte[]> kStreams : kStreamList) {
            ConsumerIterator<byte[], byte[]> consumerIte = kStreams.iterator();

            while (consumerIte.hasNext())
                System.out.println("Message consumed from topic[" + topic + "] : "       +
                                                new String(consumerIte.next().message()));
        }
        //Shutdown the consumer connector
        if (consumerConnector != null)   consumerConnector.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        ConsumerTest kafkaConsumer = new ConsumerTest();
        kafkaConsumer.initialize();         // Configure Kafka consumer
        kafkaConsumer.consume();            // Start consumption
    }
}