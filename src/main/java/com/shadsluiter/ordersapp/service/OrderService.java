package com.shadsluiter.ordersapp.service; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shadsluiter.ordersapp.data.OrderRepository;
import com.shadsluiter.ordersapp.models.OrderEntity;
import com.shadsluiter.ordersapp.models.OrderModel;

import java.util.ArrayList;
import java.util.List;

// Connects the OrderModel to the OrderEntity.  The OrderModel is used in the controller.  The OrderEntity is used in the repository.
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // helper methods used to make conversions between the OrderModel and OrderEntity
    private OrderModel convertToModel(OrderEntity orderEntity) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(String.valueOf(orderEntity.getId()));
        orderModel.setDate(orderEntity.getDate());
        orderModel.setCustomerid(String.valueOf(orderEntity.getCustomerid()));
        orderModel.setNotes(orderEntity.getNotes());
        return orderModel;
    }

    private OrderEntity convertToEntity(OrderModel orderModel) {
        OrderEntity orderEntity = new OrderEntity();
        if (orderModel.getId() != null) {
            orderEntity.setId(Long.parseLong(orderModel.getId()));
        }
        orderEntity.setDate(orderModel.getDate());
        orderEntity.setCustomerid(Long.parseLong(orderModel.getCustomerid()));
        orderEntity.setNotes(orderModel.getNotes());
        return orderEntity;
    }

    // return all orders from the database.
    public List<OrderModel> findAll() {
        
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orderModels.add(convertToModel(orderEntity));
        }
        return orderModels;
    }

    // return orders by customer id
    public List<OrderModel> findByCustomerid(String customerid) {
        List<OrderEntity> orderEntities = orderRepository.findByCustomerid(Long.valueOf(customerid));
        List<OrderModel> orderModels = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orderModels.add(convertToModel(orderEntity));
        }
        return orderModels; 
    }

    // save order
    public OrderModel save(OrderModel order) { 
        OrderEntity orderEntity = convertToEntity(order);
        orderEntity = orderRepository.save(orderEntity);
        return convertToModel(orderEntity);
    }

    public void delete(String id) {
        orderRepository.deleteById(Long.valueOf(id));
    }

    // make an update to an order. 
    public OrderModel updateOrder(String id, OrderModel order) {
        OrderEntity orderEntity = convertToEntity(order);
        orderEntity.setId(Long.valueOf(id));
        orderEntity = orderRepository.save(orderEntity);
        return convertToModel(orderEntity);
    }


}

