package com.team6.serviceme.base;

public class ResultResponse<T> extends BaseResponse<T> {

    public ResultResponse(T result) {

        super(0, null, null, result);
    }
}
