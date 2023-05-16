package com.example.memoandbook.service;

import com.example.memoandbook.domain.common.CustomException;
import com.example.memoandbook.domain.common.ErrorCode;
import com.example.memoandbook.domain.form.SignInForm;
import com.example.memoandbook.domain.form.SignUpForm;
import com.example.memoandbook.domain.model.User;
import com.example.memoandbook.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignService {
  private final UserRepository userRepository;
  private final PasswordEncoder bCryptPasswordEncoder;
  @Transactional
  public User signUp(SignUpForm form) {
    if (userRepository.existsByEmail(form.getEmail())) throw new CustomException(ErrorCode.USING_EMAIL);
    return userRepository.save(User.from(form).hashPassword(bCryptPasswordEncoder));
  }

  public User signIn(SignInForm form) {
    User user = userRepository.findByEmail(form.getEmail()).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    if (user == null) throw new CustomException(ErrorCode.USER_NOT_FOUND);
    if (!user.checkPassword(form.getPassword(), bCryptPasswordEncoder)) throw new CustomException(ErrorCode.PASSWORD_MISMATCH);

    return user;
  }
}
