package com.hsob.user.security;

import com.hsob.user.dto.user.UserDto;
import com.hsob.user.entity.user.User;
import com.hsob.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByDocument(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("User " + username + " not found.");
        UserDto userDto = modelMapper.map(user.get(), UserDto.class);
        return new UserDataDetails(userDto);
    }
}
