package com.csl.vhr.config;

import com.csl.vhr.entity.Hr;
import com.csl.vhr.entity.RespBean;
import com.csl.vhr.filter.JsonAuthenticationFilter;
import com.csl.vhr.service.HrService;
import com.csl.vhr.util.Constants;
import com.csl.vhr.util.NonNullObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

/**
 * @author MaoLongLong
 * @date 2020/8/5
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final HrService hrService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType(Constants.ContentType.APPLICATION_JSON_UTF8);
                    PrintWriter out = response.getWriter();
                    out.write(new NonNullObjectMapper().writeValueAsString(
                            RespBean.ok("注销成功")));
                    out.flush();
                    out.close();
                })

                .and()
                .csrf().disable();

        http.addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JsonAuthenticationFilter jsonAuthenticationFilter() {
        JsonAuthenticationFilter filter = new JsonAuthenticationFilter();

        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType(Constants.ContentType.APPLICATION_JSON_UTF8);
            PrintWriter out = response.getWriter();
            Hr hr = (Hr) authentication.getPrincipal();
            hr.setPassword(null);
            RespBean resp = RespBean.ok("登录成功", hr);
            out.write(new NonNullObjectMapper().writeValueAsString(resp));
            out.flush();
            out.close();
        });

        filter.setAuthenticationFailureHandler((request, response, e) -> {
            response.setContentType(Constants.ContentType.APPLICATION_JSON_UTF8);
            PrintWriter out = response.getWriter();
            RespBean resp = RespBean.error("");
            if (e instanceof BadCredentialsException) {
                resp.setMsg("用户名或密码错误");
            } else if (e instanceof LockedException) {
                resp.setMsg("账号被锁定");
            } else if (e instanceof CredentialsExpiredException) {
                resp.setMsg("密码过期");
            } else if (e instanceof AccountExpiredException) {
                resp.setMsg("账号过期");
            } else if (e instanceof DisabledException) {
                resp.setMsg("账号被禁用");
            } else {
                resp.setMsg("登录失败");
            }
            out.write(new NonNullObjectMapper().writeValueAsString(resp));
            out.flush();
            out.close();
        });

        try {
            filter.setAuthenticationManager(authenticationManagerBean());
        } catch (Exception e) {
            log.error(e.toString());
        }

        return filter;
    }

}
