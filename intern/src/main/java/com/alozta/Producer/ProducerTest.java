/**
 * Created by alozta on 8/12/16.
 */
package com.alozta.Producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Date;
import java.util.Properties;
import java.util.Random;


public class ProducerTest {

    public void Producer(Long events){
        Properties props = new Properties();
        Random rnd = new Random();

        props.put("metadata.broker.list", "broker1:9092,broker2:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "com.alozta.Producer.SimplePartitioner");
        props.put("request.required.acks", "1");

        ProducerConfig config = new ProducerConfig(props);


        Producer<String, String> producer = new Producer<String, String>(config);   // <Partition key, type of message>

        for (long nEvents = 0; nEvents < events; nEvents++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + rnd.nextInt(255);
            String msg = runtime + ",www.example.com," + ip;
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
            producer.send(data);
            data.toString();
        }
        producer.close();
    }


    public static void main(String [] args){
        ProducerTest myProcuder = new ProducerTest();
        myProcuder.Producer(new Long(100));
    }
}

