package com.imarine.kafka.Consumer;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Consumer can be specialize the data it consumes.
 * Initialize method can be also override.
 *
 * Created by alozta on 8/16/16.
 */
public class ConsumerTest extends iConsumer {

    public ConsumerTest(){
        super();
    }

    /**
     * This method can be manipulated to the needs.
     * */
    @Override
    public void consume(){
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
        iConsumer kafkaConsumer = new ConsumerTest();
        kafkaConsumer.initialize();         // Configure Kafka consumer
        kafkaConsumer.consume();            // Start consumption
    }
}
