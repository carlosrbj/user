package com.hsob.user.repository;

import com.hsob.user.entity.address.Address;
import com.hsob.user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByUser(User user);
}
