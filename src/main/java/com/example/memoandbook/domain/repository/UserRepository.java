package com.example.memoandbook.domain.repository;

import com.example.memoandbook.domain.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);
  Optional<User> findByEmail(String email);
  Optional<User> findByIdAndEmail(Long id, String email);
}
