package com.csl.vhr.config;

import com.csl.vhr.entity.Menu;
import com.csl.vhr.entity.Role;
import com.csl.vhr.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 根据用户请求地址分析请求需要的角色
 *
 * @author MaoLongLong
 * @date 2020/8/8
 */
@Component
@AllArgsConstructor
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final MenuService menuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String url = ((FilterInvocation) o).getRequestUrl();
        AntPathMatcher matcher = new AntPathMatcher();
        List<Menu> menus = menuService.getMenusWithRoles();
        for (Menu menu : menus) {
            if (matcher.match(menu.getUrl(), url)) {
                List<Role> roles = menu.getRoles();
                List<String> roleStrs = new ArrayList<>(roles.size());
                for (Role role : roles) {
                    roleStrs.add(role.getName());
                }
                return SecurityConfig.createList(roleStrs.toArray(new String[]{}));
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
