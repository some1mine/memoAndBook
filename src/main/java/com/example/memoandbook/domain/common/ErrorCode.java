package com.example.memoandbook.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 에러 타입을 지정하기 위한 Enum 입니다.
 */
@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    USING_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용중인 이메일입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "이메일에 해당하는 회원이 없습니다.");
    private final HttpStatus httpStatus;
    private final String detail;
}
