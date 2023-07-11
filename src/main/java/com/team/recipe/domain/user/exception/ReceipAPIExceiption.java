package com.team.recipe.domain.user.exception;

import org.springframework.http.HttpStatus;

// TODO : 예외 발생시 HTTP 상태 코드와 메시지를 저장하고 이를 가져오기 위한 메서드 제공
public class ReceipAPIExceiption extends RuntimeException{
    private HttpStatus status;
    private String message;

    // TODO : 생성자 생성
    public ReceipAPIExceiption(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    // TODO : 예외 메시지와 상태코드를 받아서 message 와 status 멤버 변수에 값을 할당하고, 예외 메시지 설정
    public ReceipAPIExceiption(String message, HttpStatus status, String message1){
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus(){
        return status;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
