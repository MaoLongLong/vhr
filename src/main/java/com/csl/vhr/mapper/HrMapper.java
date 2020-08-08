package com.csl.vhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.vhr.entity.Hr;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
public interface HrMapper extends BaseMapper<Hr> {

    /**
     * 根据用户名查找用户和相对应的角色
     * @param username 登录时传入的用户名
     * @return 包含角色的用户对象
     */
    Hr getHrByUsernameWithRole(String username);

}
