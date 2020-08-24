package com.csl.vhr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.vhr.entity.Menu;
import com.csl.vhr.mapper.MenuMapper;
import com.csl.vhr.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    @Cacheable(cacheNames = "menus_by_hr_id")
    public List<Menu> getMenusByHrId(Integer id) {
        return menuMapper.selectMenusByHrId(id);
    }

    @Override
    @Cacheable(cacheNames = "menus_with_roles")
    public List<Menu> getMenusWithRoles() {
        return menuMapper.selectMenusWithRoles();
    }

    @Override
    public List<Menu> getMenusWithChildren() {
        return menuMapper.selectMenusWithChildren();
    }

    @Override
    public List<Integer> getMenuIdsByRoleId(Integer id) {
        return menuMapper.selectMenuIdsByRoleId(id);
    }

}
