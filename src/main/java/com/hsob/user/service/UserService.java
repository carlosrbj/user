package com.hsob.user.service;

import com.hsob.user.dto.user.AddressDto;
import com.hsob.user.dto.user.UserDto;
import com.hsob.user.entity.address.Address;
import com.hsob.user.entity.user.User;
import com.hsob.user.repository.AddressRepository;
import com.hsob.user.repository.UserRepository;
import com.hsob.user.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private AddressRepository addressRepository;

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
            String salt = Utils.generateSalt();
            String digest = Utils.generateDigest(password, salt);
            user.setSalt(salt);
            user.setDigest(digest);
            Address address = modelMapper.map(userDto.getAddress(), Address.class);

            userRepository.save(user);
            address.setUser(user);

            addressRepository.save(address);
            user = setAddress(user.getDocument(),address);

            userRepository.save(user);
            return modelMapper.map(user, UserDto.class);
        } else {
            logger.log(Level.INFO, "password and confirm password do not match.");
            throw new IllegalArgumentException("password and confirm password do not match");
        }
    }

    private User setAddress(String document, Address address) {
        User user = userRepository.findByDocument(document);
        List<Address> list = new ArrayList<>();
        if (user != null){
            address.setUser(user);
            list.add(address);
            user.setAddresses(list);
        }
        return user;
    }

    public List<UserDto> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserDto> responseList = new ArrayList<>();
        userList.forEach(user -> {
            Address address = addressRepository.findByUser(user);
            AddressDto addressDto = modelMapper.map(address, AddressDto.class);
            UserDto userDto = modelMapper.map(user, UserDto.class);
            userDto.setAddress(addressDto);
            responseList.add(userDto);
        });
        return responseList;
    }

    public boolean validatePassword(String document, String password) {
        User user = userRepository.findByDocument(document);
        String digest = Utils.generateDigest(password, user.getSalt());
        return digest.equals(user.getDigest());
    }
}
