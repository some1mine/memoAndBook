package com.example.memoandbook.service;

import com.example.memoandbook.domain.form.SignUpForm;
import com.example.memoandbook.domain.model.User;
import com.example.memoandbook.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User signUp(SignUpForm form) {
    return userRepository.save(User.from(form));
  }
}
