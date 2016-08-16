package com.imarine.kafka.Producer;

import kafka.producer.KeyedMessage;

/**
 * Test class which extends iProducer absbtract class
 *
 * Created by alozta on 8/16/16.
 */
public class ProducerTest extends iProducer{

    public ProducerTest(){
        super();
    }

    public ProducerTest(String topic){
        super(topic);
    }

    public ProducerTest(String topic, String ip){
        super(topic,ip);
    }

    /**
     * Testing of data workflow manipulation.
     * */
    @Override
    public void Producer(){
        while(true){
            send("The cake is a lie.");
        }
    }

    /**
     * Testing of send method override.
     *
    @Override
    public void send(String msg){
        producer.send(new KeyedMessage<String, String>(topic, msg));    //it's not changed
    }*/

    public static void main(String [] args){
        iProducer myProcuder = new ProducerTest();
        myProcuder.Producer();
    }
}
