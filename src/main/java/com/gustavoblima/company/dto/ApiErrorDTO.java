package com.gustavoblima.company.dto;


public class ApiErrorDTO {
    Integer status;
    String message;

    public ApiErrorDTO(Integer status, String message) {
        this.status = status;
        this.message = message;

    }

    public ApiErrorDTO(Integer status){
        this.status = status;
        if(status == 500){
            this.message = "Internal Server Error";
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
