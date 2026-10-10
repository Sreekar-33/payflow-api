package com.payflow.api.repository;

import com.payflow.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUpiId(String upiId);

    @Query("SELECT u FROM User u WHERE u.balance > :amount")
    List<User> findUsersWithBalanceAbove(@Param("amount") Double amount);
}
