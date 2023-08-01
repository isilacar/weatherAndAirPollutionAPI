package com.isila.weatherapi.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseResponse {

    private Boolean error;
    private String msg;

}
