package com.csl.vhr.controller.config;

import com.csl.vhr.entity.Menu;
import com.csl.vhr.service.MenuService;
import com.csl.vhr.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MaoLongLong
 * @date 2020/8/6
 */
@RestController
@AllArgsConstructor
@RequestMapping("/system/config")
public class SysConfigController {

    private final MenuService menuService;

    @GetMapping("/menu")
    public List<Menu> getMenusByHrId() {
        return menuService.getMenusByHrId(SecurityUtils.currentUser().getId());
    }

}
