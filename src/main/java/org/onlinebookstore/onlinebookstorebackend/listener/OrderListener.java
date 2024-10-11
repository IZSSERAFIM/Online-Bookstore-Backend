package org.onlinebookstore.onlinebookstorebackend.listener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.onlinebookstore.onlinebookstorebackend.dto.OrderDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.server.WebSocketServer;
import org.onlinebookstore.onlinebookstorebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderListener {
    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private WebSocketServer ws;

    private OrderDTO orderDTO;

    String userName;

    @KafkaListener(topics = "order", groupId = "group_order")
    public void orderListener(ConsumerRecord<String, String> record) {
        System.out.println("Received message: " + record.value());
        // 手动解析输入字符串
        String value = record.value();
        // 分割前两个部分（日期和用户名）
        String[] mainParts = value.split(",", 3); // 仅分割前两部分，剩下的作为第三部分
        String date = mainParts[0];               // 日期
        userName = mainParts[1];               // 用户名
        // 使用正则表达式提取方括号中的列表
        String[] lists = mainParts[2].split("],\\["); // 将列表分割成 bookIdList 和 bookNumList
        String bookIdList = lists[0].replaceAll("\\[|\\]", "");   // 移除方括号
        String bookNumList = lists[1].replaceAll("\\[|\\]", "");  // 移除方括号
        // 创建 OrderDTO 对象
        OrderDTO orderDTO = new OrderDTO(date, userName, bookIdList, bookNumList);
        System.out.println(orderDTO);
        // 执行订单服务操作
        orderService.checkStockAndAddOrder(orderDTO);
        // 发送处理结果到 Kafka
        kafkaTemplate.send("order_result", "key", "order handle success");
    }



    @KafkaListener(topics = "order_result", groupId = "group_order_result")
    public void orderResultListener(ConsumerRecord<?, ?> record) throws IOException {
        System.out.println("Received message: " + record.value());
        ws.sendMessageToUser(userName, "订单处理成功");
    }
}
