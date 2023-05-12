package com.example.memoandbook.service;

import com.example.memoandbook.domain.model.User;
import com.example.memoandbook.domain.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  public Optional<User> findByIdAndEmail(Long id, String email) {
    return userRepository.findByIdAndEmail(id, email);
  }
}
