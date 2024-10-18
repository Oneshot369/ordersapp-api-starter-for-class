package com.shadsluiter.ordersapp.service;

import com.shadsluiter.ordersapp.data.UserRepository;
import com.shadsluiter.ordersapp.models.UserEntity;
import com.shadsluiter.ordersapp.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Connects the UserModel to the UserEntity.  The UserModel is used in the controller.  The UserEntity is used in the repository.
// convertToModel and convertToEntity are methods used to convert between the two.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; 

    public UserModel save(UserModel userModel) {
        UserEntity userEntity = convertToEntity(userModel);        
        UserEntity result = userRepository.save(userEntity);       
        return convertToModel(result);
    } 


    public UserModel findByLoginName(String loginName) {
        UserEntity userEntity = userRepository.findByLoginName(loginName);
        UserModel userModel = convertToModel(userEntity);
        return userModel;
    }

    // helper methods used to make conversions between the UserModel and UserEntity
    public UserModel convertToModel(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        userEntity.getId();
        String id = Long.toString(userEntity.getId());
        userModel.setId(id);
        userModel.setLoginName(userEntity.getLoginName());
        userModel.setPassword(userEntity.getPassword());
        return userModel;
    } 

    private UserEntity convertToEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        if (userModel.getId() != null) {
            userEntity.setId(Long.parseLong(userModel.getId()));
        }
        userEntity.setLoginName(userModel.getLoginName());
        userEntity.setPassword(userModel.getPassword());
        return userEntity;
    }
}
