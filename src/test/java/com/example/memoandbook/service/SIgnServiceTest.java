package com.example.memoandbook.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.memoandbook.domain.form.SignUpForm;
import com.example.memoandbook.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SignServiceTest {
  @Autowired
  private SignService signService;

  @Test
  void signUpTest() {
    SignUpForm form = SignUpForm.builder()
        .email("parkjw573@navaer.com")
        .name("jaeu")
        .password("qwer1234").build();
    User user = signService.signUp(form);
    assertEquals(user.getEmail(), form.getEmail());
    assertEquals(user.getName(), form.getName());
    assertEquals(user.getPassword(), form.getPassword());
  }
}