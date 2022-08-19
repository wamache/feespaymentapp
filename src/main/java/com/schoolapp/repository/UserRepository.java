package com.schoolapp.repository;

import com.schoolapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);



    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);


    public User findByResetPasswordToken(String token);




   // User findByEmail(String username);
}
