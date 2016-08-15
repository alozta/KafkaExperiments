# KafkaExperiments
Generic string based Kafka producer.

USAGE:
 *
 * Inside: kafka_2.11-0.9.0.0
 * bin/zookeeper-server-start.sh config/zookeeper.properties
 * bin/kafka-server-start.sh config/server.properties
 *
 * Then:
 * Run it can be run ProducerTest & ConsumerTest
 *
 * Constructors:
 * iProducer()
 * iProducer(topic)
 * iProducer(ip)         //IP REQUIRES PORT NUMBER
 * iProducer(topic,ip)   //IP REQUIRES PORT NUMBER
 *
 * Sending message:
 * iProducer.send("this is a test message.");

Project includes basic Kafka consumer to test itself.




#####Kafka release:
https://kafka.apache.org/downloads.html
