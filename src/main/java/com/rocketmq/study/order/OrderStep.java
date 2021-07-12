package com.rocketmq.study.order;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单构建者
 */
@Data
public class OrderStep {
    private long orderId;
    private String desc;

    public static List<OrderStep> buildOrders(){
        // 1039L ：创建 付款 推送 完成
        // 1065L ：创建 付款
        // 7235L ：创建 付款 推送
        List<OrderStep> orderStepList=new ArrayList<>();

        OrderStep orderDemo = new OrderStep();
        orderDemo.setOrderId(1039L);
        orderDemo.setDesc("创建");
        orderStepList.add(orderDemo);

        orderDemo= new OrderStep();
        orderDemo.setOrderId(1065L);
        orderDemo.setDesc("创建");
        orderStepList.add(orderDemo);

        orderDemo= new OrderStep();
        orderDemo.setOrderId(1039L);
        orderDemo.setDesc("付款");
        orderStepList.add(orderDemo);

        orderDemo= new OrderStep();
        orderDemo.setOrderId(7235L);
        orderDemo.setDesc("创建");
        orderStepList.add(orderDemo);


        orderDemo= new OrderStep();
        orderDemo.setOrderId(1039L);
        orderDemo.setDesc("推送");
        orderStepList.add(orderDemo);

        orderDemo= new OrderStep();
        orderDemo.setOrderId(1065L);
        orderDemo.setDesc("付款");
        orderStepList.add(orderDemo);

        orderDemo= new OrderStep();
        orderDemo.setOrderId(7235L);
        orderDemo.setDesc("付款");
        orderStepList.add(orderDemo);

        orderDemo= new OrderStep();
        orderDemo.setOrderId(1039L);
        orderDemo.setDesc("完成");
        orderStepList.add(orderDemo);

        orderDemo= new OrderStep();
        orderDemo.setOrderId(7235L);
        orderDemo.setDesc("推送");
        orderStepList.add(orderDemo);


        return orderStepList;
    }

}
