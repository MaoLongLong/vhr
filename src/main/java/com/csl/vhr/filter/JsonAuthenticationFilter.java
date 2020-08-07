package com.csl.vhr.filter;

import com.csl.vhr.entity.LoginBean;
import com.csl.vhr.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
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


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!Constants.RequestMethod.POST.equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        if (Constants.ContentType.APPLICATION_JSON.equals(request.getContentType())
                || Constants.ContentType.APPLICATION_JSON_UTF8.equals(request.getContentType())) {

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
