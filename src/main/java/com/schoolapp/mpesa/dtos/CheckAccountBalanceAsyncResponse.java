package com.schoolapp.mpesa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckAccountBalanceAsyncResponse {

    @JsonProperty("Result")
    private Result result;
}
