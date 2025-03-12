package com.td005.jobportal.repository;

import com.td005.jobportal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByEmail(String email);



}
