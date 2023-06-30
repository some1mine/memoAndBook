package com.example.memoandbook.controller;

import com.example.memoandbook.config.jwt.JwtAuthenticationProvider;
import com.example.memoandbook.domain.common.CustomException;
import com.example.memoandbook.domain.common.ErrorCode;
import com.example.memoandbook.domain.common.UserVo;
import com.example.memoandbook.domain.dto.BookDto;
import com.example.memoandbook.domain.dto.MemoDto;
import com.example.memoandbook.domain.dto.UserBookDto;
import com.example.memoandbook.domain.form.MemoForm;
import com.example.memoandbook.domain.model.User;
import com.example.memoandbook.service.BookService;
import com.example.memoandbook.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final JwtAuthenticationProvider provider;
  private final BookService bookService;
  private final UserService userService;
  @GetMapping("/searchBook")
  public ResponseEntity<List<BookDto>> searchBook(@RequestHeader("X-AUTH-TOKEN") String token, @RequestParam String keyWord) {
    UserVo userVo = provider.getUserVo(token);
    userService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    return ResponseEntity.ok(bookService.searchBook(keyWord).stream().map(BookDto::from).collect(
        Collectors.toList()));
  }

  @GetMapping("/findBook")
  public ResponseEntity<BookDto> findABook(@RequestHeader("X-AUTH_TOKEN") String token, @RequestParam String isbn13) {
    UserVo userVo = provider.getUserVo(token);
    userService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    return ResponseEntity.ok(BookDto.from(bookService.findABook(isbn13)));
  }

  @PostMapping("saveBook")
  public ResponseEntity<UserBookDto> saveBook(@RequestHeader("X-AUTH_TOKEN") String token, @RequestBody String isbn13) {
    UserVo userVo = provider.getUserVo(token);
    User user = userService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    return ResponseEntity.ok(UserBookDto.from(bookService.saveInMyBook(isbn13, user)));
  }

  @PostMapping("saveMemo")
  public ResponseEntity<MemoDto> saveMemo(@RequestHeader("X-AUTH_TOKEN") String token, @RequestBody MemoForm form) {
    UserVo userVo = provider.getUserVo(token);
    userService.findByIdAndEmail(userVo.getId(), userVo.getEmail())
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

    return ResponseEntity.ok(MemoDto.from(bookService.saveMemoInMyBook(form)));
  }
}

