package com.csl.vhr.util;

import com.csl.vhr.entity.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author MaoLongLong
 * @date 2020/8/6
 */
public class SecurityUtils {

    private SecurityUtils() {
    }

    public static Hr currentUser() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
