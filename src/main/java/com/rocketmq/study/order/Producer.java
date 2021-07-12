package com.rocketmq.study.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //1.创建消息生产者producer,并制定生产者组名
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("group1");
        //2.指定NameServer地址
        defaultMQProducer.setNamesrvAddr("1.15.235.58:9876;8.140.25.64:9876");
        //3.启动producer
        defaultMQProducer.start();

        List<OrderStep> orderStepList = OrderStep.buildOrders();

        //发送消息
        for (int i = 0; i < orderStepList.size(); i++) {
            String body=orderStepList.get(i)+"";
            Message msg = new Message("OrderTopic","Order","i"+i,body.getBytes());
            /**
             * 参数一：消息对象
             * 参数二：消息队列的选择器
             * 参数三：选择队列的业务标识（订单Id)
             */
            SendResult result = defaultMQProducer.send(msg, new MessageQueueSelector() {
                /**
                 * @param list 队列集合
                 * @param message 消息对象
                 * @param o 业务标识的参数
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    // 这个o就是send方法的第三个参数orderStepList.get(i).getOrderId()
                    long orderId = (long) o;
                    long index = orderId % list.size();
                    return list.get((int) index);
                }
            }, orderStepList.get(i).getOrderId());
            System.out.println("发送结果"+result);
        }

        // 关闭producer
        defaultMQProducer.shutdown();
    }
}
