package com.rocketmq.study.base.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;


/**
 * 发送异步消息类（发送消息后不等待mq服务器响应就返回）
 */
public class AsyncProducer {
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
            Message msg = new Message("base","Tag2",("Hello World"+i).getBytes());
            //5.发送异步消息
            defaultMQProducer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送结果："+sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送异常："+throwable);
                }
            });
            Thread.sleep(1000);
        }
        //6.关闭生产者producer
        defaultMQProducer.shutdown();
    }
}
