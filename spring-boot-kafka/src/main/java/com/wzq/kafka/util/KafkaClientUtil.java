package com.wzq.kafka.util;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.alibaba.fastjson.JSON;
import com.wzq.kafka.consumer.KafkaProperties;
import com.wzq.kafka.entity.KafkaTopicBean;
import kafka.admin.TopicCommand;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.acl.AclBindingFilter;
import org.apache.kafka.common.resource.ResourcePatternFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class KafkaClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(KafkaClientUtil.class);

    private String broker;
    private AdminClient client;

    public KafkaClientUtil(String broker){
        this.broker = broker;
        createClient();
    }

    void createClient(){
        if(client != null){
            client.close();
            client = null;
        }
        try {
            Properties adminProp = new Properties();
            adminProp.put("bootstrap.servers", broker);
            client = AdminClient.create(adminProp);
            logger.info("===========初始化adminClient成功=================");
        }catch (Exception e){
            logger.error("初始化AdminClient失败！", e);
        }
    }


    public void getTopicInfo(String topicName){
        DescribeTopicsResult topicsResult = client.describeTopics(Arrays.asList(topicName));
        KafkaFuture<Map<String, TopicDescription>> allInfo = topicsResult.all();

        try {
            Map<String, TopicDescription> all = allInfo.get();
            logger.info("all info>>>>>>{}", JSON.toJSONString(all));
            TopicDescription relatedTopic = all.get(topicName);
            logger.info("current topic: {}, info: {} : {}", topicName, relatedTopic.toString(), relatedTopic.isInternal());
        } catch (Exception e){
            logger.error("get TopicInfo error", e);
        }
    }

    public boolean existTopic(String topicName){
        boolean exist = false;
        DescribeTopicsResult topicsResult = client.describeTopics(Arrays.asList(topicName));
        KafkaFuture<TopicDescription> kafkaFuture = topicsResult.values().get(topicName);
        try {
            TopicDescription topicDescription = kafkaFuture.get();
            exist = true;
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }

        logger.info("topic exist : ", exist);

        return exist;
    }

    public void deleteTopic(List<String> topicNames){
        try {
            DeleteTopicsOptions deleteTopicsOptions = new DeleteTopicsOptions();
            deleteTopicsOptions.timeoutMs(100);
            client.deleteTopics(topicNames);
            logger.info("delete topic result");
        }catch (Exception e){
            logger.error("delete topic error", e);
        }
    }

    /**
     * 创建topic
     * @param kafkaTopicBeans
     */
    public void createTopic(List<KafkaTopicBean> kafkaTopicBeans){
        try {
            List<NewTopic> newTopics = new ArrayList<>();
            kafkaTopicBeans.forEach(kafkaTopicBean -> {
                logger.info("topic {} info: {}", kafkaTopicBean);
                NewTopic newTopic = new NewTopic(kafkaTopicBean.getTopicName(), kafkaTopicBean.getPartition(), kafkaTopicBean.getReplication());
                newTopics.add(newTopic);
            });

            CreateTopicsOptions createTopicsOptions = new CreateTopicsOptions();
            createTopicsOptions.validateOnly(true);
            createTopicsOptions.timeoutMs(2000);
            CreateTopicsResult topicsResult = client.createTopics(newTopics, createTopicsOptions);
            Map<String, KafkaFuture<Void>> values = topicsResult.values();
            values.forEach((topic, kafkaFuture) -> {
                logger.info("^^^^^^^^^^^^^^{}", topic);
                kafkaFuture.whenComplete((a, b)->{
                    logger.info("------------------", a);
                });
            });
            logger.info("create topic success");
        } catch(Exception e) {
            logger.error("create topic error, topicInfos: {}", kafkaTopicBeans, e);
        }
    }

    public List<TopicListing> listAllTopic(){
        List<TopicListing> allTopic = new ArrayList<>();
        try {
            Collection<TopicListing> topicListings = client.listTopics().listings().get();
            allTopic.addAll(topicListings);
        } catch (InterruptedException e) {
            logger.error("1 list topic error", e);
        } catch (ExecutionException e) {
            logger.error("2 list topic error", e);
        }
        return allTopic;
    }

    public AdminClient getClient(){
        return this.client;
    }

    public static void main(String[] args) {
        String server = KafkaProperties.KAFKA_SERVER;
        KafkaClientUtil kafkaClientUtil = new KafkaClientUtil(server);

        List<TopicListing> topicListings = kafkaClientUtil.listAllTopic();
        System.err.println(topicListings.toString());

        KafkaTopicBean kafkaTopicBean = new KafkaTopicBean();
        kafkaTopicBean.setTopicName("tt");
        kafkaTopicBean.setReplication((short)1);
        kafkaTopicBean.setPartition(4);
        kafkaClientUtil.createTopic(Arrays.asList(kafkaTopicBean));
//        kafkaClientUtil.deleteTopic(Arrays.asList("topic2"));


//        try {
//            AdminClient client = kafkaClientUtil.getClient();
//            KafkaFuture<Collection<ConsumerGroupListing>> allInfo = client.listConsumerGroups().all();
//            System.err.println(allInfo.get().toString());
//
//            ListConsumerGroupOffsetsResult simpleConsumerDemoClient = client.listConsumerGroupOffsets("SimpleConsumerDemoClient");
//            KafkaFuture<Map<TopicPartition, OffsetAndMetadata>> mapKafkaFuture = simpleConsumerDemoClient.partitionsToOffsetAndMetadata();
//            System.err.println(mapKafkaFuture.get().toString());
//
//            DescribeClusterResult clusterResult = client.describeCluster();
//            KafkaFuture<Collection<Node>> nodes = clusterResult.nodes();
//            System.err.println(nodes.get().toString());
//
//            DescribeTopicsResult topic2 = client.describeTopics(Arrays.asList("replicated-topic", "tt"));
//            Map<String, KafkaFuture<TopicDescription>> values = topic2.values();
//            KafkaFuture<TopicDescription> topicDescription = values.get("tt");
//            TopicDescription topicDescriptionInfo = topicDescription.get();
//            System.err.println(">>>>>>>>>"+topicDescriptionInfo.toString());
//
//        } catch (Exception e){
//            System.err.println("=================================");
//        }


    }

}
