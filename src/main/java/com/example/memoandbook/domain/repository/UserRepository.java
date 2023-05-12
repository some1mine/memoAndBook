package com.example.memoandbook.domain.repository;

import com.example.memoandbook.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
