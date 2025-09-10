package com.sujay.journalApplication.service;

import com.sujay.journalApplication.entity.User;
import com.sujay.journalApplication.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    //this method will be used in @PutMapping in User controller & other controllers to find user based on userName
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
