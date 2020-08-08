package com.csl.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.vhr.entity.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户id获取该用户可以操作的菜单
     * @param id 用户id
     * @return 菜单列表
     */
    List<Menu> getMenusByHrId(Integer id);

    /**
     * 返回包含角色的菜单列表
     * @return 菜单列表
     */
    List<Menu> getMenusWithRoles();

}
