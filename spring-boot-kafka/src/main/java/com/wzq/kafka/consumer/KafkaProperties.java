package com.wzq.kafka.consumer;

/**
 *
 * @author
 */
public class KafkaProperties {
    public static final String TOPIC = "topic123";
    public static final String KAFKA_SERVER = "120.79.8.31:9092,120.79.8.31:9093,120.79.8.31:9094";
    public static final String CLIENT_ID = "SimpleConsumerDemoClient";
    public static final Integer PARTITION = 4;
    public static final short REPLICATION = 1;
    private KafkaProperties() {}
}