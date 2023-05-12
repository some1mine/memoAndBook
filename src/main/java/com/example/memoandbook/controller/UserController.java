package com.example.memoandbook.controller;

import com.example.memoandbook.domain.dto.UserDto;
import com.example.memoandbook.domain.form.SignUpForm;
import com.example.memoandbook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("signup")
  public ResponseEntity<UserDto> signUp(@RequestBody SignUpForm form) {
    return ResponseEntity.ok(UserDto.from(userService.signUp(form)));
  }
}
