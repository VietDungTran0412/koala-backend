package com.example.koalasystem.repository;

import com.example.koalasystem.entity.embedded.Customer;
import com.example.koalasystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(Customer customer);
}
