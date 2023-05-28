package com.md.homework2.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> implements Serializable {

    private T data;
    private LocalDateTime responseDate;
    private boolean isSuccess;
    private String messages;

    public RestResponse(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.responseDate = LocalDateTime.now();
    }

    public RestResponse(String message, boolean isSuccess) {
        this.isSuccess = isSuccess;
        this.messages = message;
        this.responseDate = LocalDateTime.now();
    }

    public static <T> RestResponse<T> of(T t){
        return new RestResponse<>(t, true);
    }

    public static <T> RestResponse<T> empty(){
        return new RestResponse<>(null, true);
    }

    public static <T> RestResponse<T> error(T t){
        return new RestResponse<>(t, false);
    }

    public static <T> RestResponse<T> errorMessage(String messages) {
        return new RestResponse<>(messages, false);
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

}
