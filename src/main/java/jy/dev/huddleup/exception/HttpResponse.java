package jy.dev.huddleup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum HttpResponse {
    OK(HttpStatus.OK, "OK"),

    INVALID_ID_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 아이디 혹은 패스워드 입니다"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다"),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다");


    private org.springframework.http.HttpStatus httpStatus;
    private String message;

    HttpResponse(org.springframework.http.HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ResponseEntity<String> getResponseEntity(){
        return ResponseEntity.status(httpStatus).body(message);
    }
}