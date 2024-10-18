package com.shadsluiter.ordersapp.controllers; 

import com.shadsluiter.ordersapp.models.OrderModel;
import com.shadsluiter.ordersapp.models.UserModel;
import com.shadsluiter.ordersapp.service.OrderService;
import com.shadsluiter.ordersapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

// This class is a REST controller. Use PostMan to test the API or develop a separate front-end application to interact with the API.
@RestController
@RequestMapping("/api/orders")

@CrossOrigin(origins = "http://127.0.0.1:5500") // allow requests from this origin. Only clients from this IP and port can make requests to this API
public class OrdersController {

    @Autowired
    private OrderService orderService; 

    @Autowired
    private UserService userService;

    // create order. Must use PostMan or a front-end application to test this API to perform DELETE, POST, PUT requests
    @PostMapping
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel order, Authentication auth ) { 

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        UserModel user = userService.findByLoginName(userDetails.getUsername());
        
       // no username set for simplicity. use 10 as default customerid (shad login name is id 10 in the database)
        order.setCustomerid(user.getId());

        if(order.getDate() == null){
            order.setDate(new Date());
        }

        OrderModel savedOrder = orderService.save(order);  
        return ResponseEntity.ok(savedOrder);
    }

    // get requests can be done with a browser. test with chrome or firefox on http://localhost:8080/api/orders
    @GetMapping
    public ResponseEntity<List<OrderModel>> getAllOrders() {
        List<OrderModel> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    // get orders by customer id. test with http://localhost:8080/api/orders/10 (10 is the customer id)
    @GetMapping("/{customerid}")
    public ResponseEntity<List<OrderModel>> getOrdersByCustomerId(@PathVariable String customerid) {
        List<OrderModel> orders = orderService.findByCustomerid(customerid);
        return ResponseEntity.ok(orders);
    }
 
    // delete order. Must use PostMan or a front-end application to test this API to perform DELETE, POST, PUT requests
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

    // update order. Must use PostMan or a front-end application to test this API to perform DELETE, POST, PUT requests
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable String id, @RequestBody OrderModel order) {
        if(order == null){
            return new ResponseEntity<>("Error: order cannot be null", HttpStatus.BAD_REQUEST);
        }
        if(order.getDate() == null){
            order.setDate(new Date());
        }
        try{
            if(order.getNotes() == null || order.getNotes().isEmpty()){
                return new ResponseEntity<>("Error: notes cannot be null or empty", HttpStatus.BAD_REQUEST);
            }

            OrderModel updatedOrder = orderService.updateOrder(id, order);

            if(updatedOrder == null){
                return new ResponseEntity<>("Error: order not found", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Order update successful", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

