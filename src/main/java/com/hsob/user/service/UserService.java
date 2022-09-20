package com.hsob.user.service;

import com.hsob.documentdb.user.Address;
import com.hsob.documentdb.user.QUser;
import com.hsob.documentdb.user.User;
import com.hsob.user.dto.user.UserDto;
import com.hsob.user.repository.UserRepository;
import com.hsob.user.utils.Utils;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserDto saveUser(UserDto userDto, String password, String confirmPassword) {

        if (password.isEmpty() || password.equals(confirmPassword)){
            User user = modelMapper.map(userDto, User.class);
            if (password.isEmpty()){
                password = "123";
            }
            if (userDto.getSocial_name() != null){
                user.setName(userDto.getSocial_name());
            }
            Address address = modelMapper.map(userDto.getAddress(), Address.class);
            List<Address> addressList = new ArrayList<>();
            addressList.add(address);
            user.setAddresses(addressList);
            String salt = Utils.generateSalt();
            String digest = Utils.generateDigest(password, salt);
            user.setSalt(salt);
            user.setDigest(digest);
            userRepository.save(user);
            return modelMapper.map(user, UserDto.class);
        } else {
            logger.log(Level.INFO, "password and confirm password do not match.");
            throw new IllegalArgumentException("password and confirm password do not match");
        }
    }
}
