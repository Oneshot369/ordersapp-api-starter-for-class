package com.shadsluiter.ordersapp.data;  


import org.springframework.data.jpa.repository.JpaRepository;

import com.shadsluiter.ordersapp.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // custom query method that is not inherited from JpaRepository
    UserEntity findByLoginName(String loginName);

    // implied methods inherited from JpaRepository
    // List<UserEntity> findAll();
    // void deleteById(Long id);
    // UserEntity save(UserEntity user);
    // UserEntity findById(Long id);
    // boolean existsById(Long id);
    // long count();
    // void delete(UserEntity user);
    // void deleteAll();
    // void deleteAll(Iterable<? extends UserEntity> users);
    // List<UserEntity> findAllById(Iterable<Long> ids);
    // List<UserEntity> saveAll(Iterable<UserEntity> users);
    // void flush();
    // void deleteInBatch(Iterable<UserEntity> users);
    // void deleteAllInBatch();
    // UserEntity getOne(Long id);
    // <S extends UserEntity> List<S> saveAll(Iterable<S> entities);
    // <S extends UserEntity> S saveAndFlush(S entity);
    // <S extends UserEntity> List<S> saveAllAndFlush(Iterable<S> entities);
    // void deleteAllByIdInBatch(Iterable<Long> ids);
    // void deleteAllInBatch(Iterable<UserEntity> users);

}

