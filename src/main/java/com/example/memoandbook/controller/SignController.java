package com.example.memoandbook.controller;

import com.example.memoandbook.config.JwtAuthenticationProvider;
import com.example.memoandbook.domain.dto.UserDto;
import com.example.memoandbook.domain.form.SignInForm;
import com.example.memoandbook.domain.form.SignUpForm;
import com.example.memoandbook.domain.model.User;
import com.example.memoandbook.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
@RequiredArgsConstructor
public class SignController {
  private final SignService signService;
  private final JwtAuthenticationProvider provider;

  @PostMapping("/signup")
  public ResponseEntity<UserDto.Response> signUp(@RequestBody SignUpForm form) {
    return ResponseEntity.ok(UserDto.Response.from(signService.signUp(form)));
  }

  @PostMapping("/signIn")
  public ResponseEntity<String> signIn(@RequestBody SignInForm form) {
    User user = signService.signIn(form);
    return ResponseEntity.ok(provider.createToken(user.getEmail(), user.getId()));
  }
}
