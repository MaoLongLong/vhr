package com.csl.vhr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author MaoLongLong
 * @date 2020/8/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespBean {

    private Integer status;

    private String msg;

    private Object obj;

    public static RespBean ok(String msg) {
        return new RespBean(HttpStatus.OK.value(), msg, null);
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(HttpStatus.OK.value(), msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(HttpStatus.BAD_REQUEST.value(), msg, null);
    }

}
