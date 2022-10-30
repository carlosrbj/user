package com.hsob.user.service;

import com.hsob.user.model.user.AddressRequest;
import com.hsob.user.model.user.UserRequest;
import com.hsob.user.entity.address.Address;
import com.hsob.user.entity.user.User;
import com.hsob.user.repository.AddressRepository;
import com.hsob.user.repository.UserRepository;
import com.hsob.user.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public UserRequest saveUser(UserRequest userRequest) {

        if (userRequest.getPassword().isEmpty() || userRequest.getPassword().equals(userRequest.getConfirm_password())){
            User user = modelMapper.map(userRequest, User.class);
            if (userRequest.getPassword().isEmpty()){
                userRequest.setPassword("123");
            }
            String salt = Utils.generateSalt();
            String digest = Utils.generateDigest(userRequest.getPassword(), salt);
            user.setSalt(salt);
            user.setDigest(digest);
            user.setAuthpass(passwordEncoder.encode(userRequest.getPassword()));

            userRepository.save(user);

            if (userRequest.getAddress() != null){
                Address address = modelMapper.map(userRequest.getAddress(), Address.class);
                address.setUser(user);
                addressRepository.save(address);
                user = setAddress(user.getDocument(),address);
                userRepository.save(user);
            }
            return modelMapper.map(user, UserRequest.class);
        } else {
            logger.log(Level.INFO, "password and confirm password do not match.");
            throw new IllegalArgumentException("password and confirm password do not match");
        }
    }

    private User setAddress(String document, Address address) {
        Optional<User> user = userRepository.findByDocument(document);
        List<Address> list = new ArrayList<>();
        if (user.isPresent()){
            address.setUser(user.get());
            list.add(address);
            user.get().setAddresses(list);
        }
        return user.get();
    }

    public List<UserRequest> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserRequest> responseList = new ArrayList<>();
        userList.forEach(user -> {
            Address address = addressRepository.findByUser(user);
            AddressRequest addressRequest = modelMapper.map(address, AddressRequest.class);
            UserRequest userRequest = modelMapper.map(user, UserRequest.class);
            userRequest.setAddress(addressRequest);
            responseList.add(userRequest);
        });
        return responseList;
    }

    public boolean validatePassword(String document, String password) {
        Optional<User> user = userRepository.findByDocument(document);
        if (user.isPresent()){
            String digest = Utils.generateDigest(password, user.get().getSalt());
            if (digest.equals(user.get().getDigest())){
                return true;
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "password informed is invalid");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with document " + document + " not found");
        }
    }

    public boolean validateAuthpass(String document, String password) {
        Optional<User> user = userRepository.findByDocument(document);
        if (user.isPresent()){
            if (passwordEncoder.matches(password, user.get().getAuthpass())){
                return true;
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "password informed is invalid");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with document " + document + " not found");
        }
    }
}
