/**
 * iProducer abstract class.
 * In order to use this class you should write a class which extends this one.
 * If you don't want to use taking user input and send it via Kafka consumer you can override Producer function.
 * Overriding send function is not advised.
 *
 *
 * @author alozta
 *
 * USAGE:
 *
 * Inside: kafka_2.11-0.9.0.0
 * bin/zookeeper-server-start.sh config/zookeeper.properties
 * bin/kafka-server-start.sh config/server.properties
 *
 * Then:
 * Run it can be run ProducerTest & ConsumerTest
 *
 * Sending message:
 * childClass.send("this is a test message.");
 *
 * Created by alozta on 8/12/16.
 *
 * Message producer for Kafka.
 */
package com.imarine.kafka.Producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.Scanner;


public abstract class iProducer {

    Producer<String, String> producer;
    String topic="notification";
    String ip="0.0.0.0:9092";       //Local default of kafka

    //CONSTRUCTORS
    /**
     * Set default properties and run producer
     * */
    public iProducer(){
        System.out.println("Initiated with local settings:\nTopic name: "+this.topic+"\nIP:"+this.ip);

        Properties props = new Properties();
        //SET PROPERTIES
        props.put("metadata.broker.list", ip);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.imarine.kafka.Producer.SimplePartitioner");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<>(config);   // Producer<Partition key, type of message>
    }

    /**
     * Change topic name and run producer
     * @param topic Kafka topic name
     * */
    public iProducer(String topic){
        this.topic=topic;
        System.out.println("Initiated with edited settings:\nTopic name: "+this.topic+"\nIP:"+this.ip);

        Properties props = new Properties();
        //SET PROPERTIES
        props.put("metadata.broker.list", ip);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.imarine.kafka.Producer.SimplePartitioner");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<>(config);   // Producer<Partition key, type of message>
    }

    /**
     * Change IP address and run producer
     * @param ip IP address (requires port number)
     *
    public iProducer(String ip, int noUse){
        this.ip=ip;          //Local default of kafka
        System.out.println("Initiated with edited settings:\nTopic name: "+this.topic+"\nIP:"+this.ip);

        Properties props = new Properties();
        //SET PROPERTIES
        props.put("metadata.broker.list", ip);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.imarine.kafka.Producer.SimplePartitioner");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<>(config);   // Producer<Partition key, type of message>
    }*/

    /**
     * Change topic name and IP address and run producer
     * @param topic Kafka topic name
     * @param ip IP address (requires port number, i.e. 0.0.0.0:9092)
     * */
    public iProducer(String topic, String ip){
        this.topic=topic;
        this.ip=ip;
        System.out.println("Initiated with edited settings:\nTopic name: "+this.topic+"\nIP:"+this.ip);

        Properties props = new Properties();
        //SET PROPERTIES
        props.put("metadata.broker.list", ip);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.imarine.kafka.Producer.SimplePartitioner");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<>(config);   // Producer<Partition key, type of message>
    }
    //CONSTRUCTORS

    /**
     * This method can be changed to adapt workflow of data.
     *
     * Default: Takes user input and sends it via Kafka producer indefinitely.
     */
    public void Producer(){
        while (true){
            Scanner reader = new Scanner(System.in);
            System.out.print("input: ");
            String input = reader.nextLine();
            send(input);   //send msg to notification partition
        }

        //producer.close();         //use this for non-infinite loops
    }

    /**
     * Sends String it via Kafka producer
     */
    public void send(String msg){
        producer.send(new KeyedMessage<String, String>(topic, msg));
    }
}

