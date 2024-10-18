package com.shadsluiter.ordersapp.data; 
import org.springframework.data.jpa.repository.JpaRepository; 
import com.shadsluiter.ordersapp.models.OrderEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // custom query method that is not inherited from JpaRepository
    List<OrderEntity> findByCustomerid(Long customerid);

    // implied methods from JpaRepository
    // List<OrderEntity> findAll();
    // void deleteById(Long id);
    // OrderEntity save(OrderEntity order);
    // OrderEntity findById(Long id);
    // boolean existsById(Long id);
    // long count();
    // void delete(OrderEntity order);
    // void deleteAll();
    // void deleteAll(Iterable<? extends OrderEntity> orders);
    // List<OrderEntity> findAllById(Iterable<Long> ids);
    // List<OrderEntity> saveAll(Iterable<OrderEntity> orders);
    // void flush();
    // void deleteInBatch(Iterable<OrderEntity> orders);
    // void deleteAllInBatch();
    // OrderEntity getOne(Long id);
    // <S extends OrderEntity> List<S> saveAll(Iterable<S> entities);
    // <S extends OrderEntity> S saveAndFlush(S entity);
    // <S extends OrderEntity> List<S> saveAllAndFlush(Iterable<S> entities);
    // void deleteAllByIdInBatch(Iterable<Long> ids);
    // void deleteAllInBatch(Iterable<OrderEntity> orders);
    // void deleteAllById(Iterable<? extends Long> ids); 

}

