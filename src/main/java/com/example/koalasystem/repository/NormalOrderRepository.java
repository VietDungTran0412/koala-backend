package com.example.koalasystem.repository;

import com.example.koalasystem.entity.NormalOrder;
import com.example.koalasystem.entity.Order;
import com.example.koalasystem.enums.NormalOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormalOrderRepository extends JpaRepository<NormalOrder, Long> {
    List<NormalOrder> findAllByOrderStatus(NormalOrderStatus status);
}
