package com.zx.config;

import com.zx.utils.Result;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuLiException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;
    
    @Override
    public String toString() {
        return "GuliException{" +
        "message=" + this.getMessage() +
        ", code=" + code +
        '}';
    }
}

