package com.csl.vhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.vhr.entity.Menu;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id获取该用户可以操作的菜单
     * @param id 用户id
     * @return 菜单列表
     */
    List<Menu> selectMenusByHrId(Integer id);

    /**
     * 返回包含角色的菜单列表
     * @return 菜单列表
     */
    List<Menu> selectMenusWithRoles();

    /**
     * 返回包含子菜单的菜单列表
     * @return 菜单列表
     */
    List<Menu> selectMenusWithChildren();

    List<Integer> selectMenuIdsByRoleId(Integer id);
}
