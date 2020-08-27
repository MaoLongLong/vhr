package com.csl.vhr.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

/**
 * @author MaoLongLong
 * @date 2020/8/5
 */
@Slf4j
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Data
    static class LoginBean {
        private String username;
        private String password;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        if ("application/json".equals(request.getContentType())
                || "application/json;charset=UTF-8".equals(request.getContentType())) {

            String username;
            String password;

            try (Reader reader = request.getReader()) {
                LoginBean loginBean = new ObjectMapper().readValue(reader, LoginBean.class);
                username = loginBean.getUsername();
                password = loginBean.getPassword();
            } catch (IOException e) {
                log.error(e.toString());
                throw new AuthenticationServiceException("json parse fail");
            }

            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);

            setDetails(request, authRequest);

            return super.getAuthenticationManager().authenticate(authRequest);
        }
        return super.attemptAuthentication(request, response);
    }

}
