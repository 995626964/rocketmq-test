package com.rocketmq.study.base.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 发送单向消息（不关心发送结果）
 */
public class OneWayProducer {
    public static void main(String[] args) throws RemotingException, InterruptedException, MQClientException {
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
            Message msg = new Message("base","Tag3",("Hello World,单向消息"+i).getBytes());
            //5.发送异步消息
            defaultMQProducer.sendOneway(msg);
            Thread.sleep(1000);
        }
        //6.关闭生产者producer
        defaultMQProducer.shutdown();
    }
}
