package com.csl.vhr.controller;

import com.csl.vhr.entity.RespBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaoLongLong
 * @date 2020/8/5
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<RespBean> login() {
        return new ResponseEntity<>(
                RespBean.builder().status(401).msg("请登录").build(), HttpStatus.UNAUTHORIZED);
    }

}
