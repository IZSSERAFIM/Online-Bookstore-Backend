package org.onlinebookstore.onlinebookstorebackend.listener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.onlinebookstore.onlinebookstorebackend.dto.OrderDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.Order;
import org.onlinebookstore.onlinebookstorebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private OrderDTO orderDTO;

    @KafkaListener(topics = "order", groupId = "group_order")
    public void orderListener(ConsumerRecord<String, String> record) {
        System.out.println("Received message: " + record.value());
        String[] data = record.value().split(",");
        orderDTO = new OrderDTO(data[0], data[1], data[2], data[3]);
        orderService.checkStockAndAddOrder(orderDTO);
        kafkaTemplate.send("order_result", "key", "order handle success");
    }

    @KafkaListener(topics = "order_result", groupId = "group_order_result")
    public void orderResultListener(ConsumerRecord<?, ?> record) {
        System.out.println("Received message: " + record.value());
    }
}
