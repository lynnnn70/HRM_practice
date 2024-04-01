package com.example.HRM_practice.common;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class CommonResponse<T extends CommonResponse<T, B>, B> {

    @NotNull
    @Schema(description = "status number")
    private Integer status;

    @NotNull
    @Schema(description = "message of result")
    private String errorMessage;

    @Schema(description = "body of response")
    private T body;

    public CommonResponse(Integer status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }


    //StatusCode.OK 返回的是 OK 這個列舉型別的實例
    //先定義預設為status.OK，因此在new CommonResponse時，若結果為成功時就不須再重覆寫 EX:
    //寫 CommonResponse<String> defaultResponse = new CommonResponse<>();
    //就會等同於 CommonResponse<String> defaultResponse = new CommonResponse<>(StatusCode.OK.getValue(), "success");
    public CommonResponse(){
        this(StatusCode.OK.getValue(), "success");
    }

    public Integer getStatus() {
        return status;
    }

    public T setStatus(Integer status) {
        this.status = status;
        return (T)this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return (T)this;
    }

    public T getBody() {
        return body;
    }

    public T setBody(T body) {
        this.body = body;
        return (T)this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{status:").append(status);
        sb.append(", errorMessage:'").append(errorMessage).append('\'');
        sb.append(", body:").append(body);
        sb.append("}");
        return sb.toString();
    }
}
