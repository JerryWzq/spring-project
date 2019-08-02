package com.wzq.kafka.main;

import com.wzq.kafka.consumer.Consumer;
import com.wzq.kafka.consumer.KafkaProperties;
import com.wzq.kafka.producer.Producer;

/**
 *
 * @author
 */
public class KafkaConsumerProducerDemo {
    public static void main(String[] args) {
        boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
        System.err.println(">>>>>>>"+isAsync);
//        Producer producerThread = new Producer(KafkaProperties.TOPIC, isAsync);
//        producerThread.start();

        Consumer consumerThread = new Consumer(KafkaProperties.TOPIC);
        consumerThread.start();
    }
}