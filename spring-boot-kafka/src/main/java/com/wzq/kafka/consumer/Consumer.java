package com.wzq.kafka.consumer;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author
 */
public class Consumer extends ShutdownableThread {
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;

    public Consumer(String topic) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaProperties.CLIENT_ID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        this.topic = topic;

        //从特定的position消费数据
//        List<TopicPartition> topicPartitionList = new ArrayList<>();
//        for(int i=0; i<4; i++){
//            TopicPartition topicPartition = new TopicPartition(topic, i);
//            topicPartitionList.add(topicPartition);
//        }
//        consumer.assign(topicPartitionList);
//        consumer.seekToBeginning(topicPartitionList);

        //订阅模式， 即当生产者生产数据时会收到数据
        consumer.subscribe(Collections.singletonList(this.topic));
    }

    @Override
    public void doWork() {
        ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1));
        for (ConsumerRecord<Integer, String> record : records) {
            System.err.println("Received message: [" + record.key() + ", " + record.value() + "] at partition: [" +record.partition()+ "] offset: [" + record.offset() + "]");
        }
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }
}