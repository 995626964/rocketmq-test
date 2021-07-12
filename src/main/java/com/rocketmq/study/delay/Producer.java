package com.rocketmq.study.delay;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //1.创建消息生产者producer,并制定生产者组名
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("group1");
        //2.指定NameServer地址
        defaultMQProducer.setNamesrvAddr("1.15.235.58:9876;8.140.25.64:9876");
        //3.启动producer
        defaultMQProducer.start();
        for (int i = 0; i < 10; i++) {
            //4.创建消息对象，指定主题Topic,Tag和消息体
            /**
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message msg = new Message("TopicDelay","Tag1",("Hello World"+i).getBytes());
            // 设置延迟时间
            msg.setDelayTimeLevel(2);
            //5.发送消息
            SendResult result = defaultMQProducer.send(msg);
            System.out.println(result.toString());
            Thread.sleep(1000);
        }
        //6.关闭生产者producer
        defaultMQProducer.shutdown();
    }
}
