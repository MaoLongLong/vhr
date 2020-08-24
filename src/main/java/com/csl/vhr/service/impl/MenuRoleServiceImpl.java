package com.csl.vhr.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.vhr.entity.MenuRole;
import com.csl.vhr.mapper.MenuRoleMapper;
import com.csl.vhr.service.MenuRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    private final MenuRoleMapper menuRoleMapper;

    @Override
    @Transactional
    public boolean updateMenuRole(Integer id, Integer[] ids) {

        menuRoleMapper.delete(Wrappers.<MenuRole>lambdaUpdate().eq(MenuRole::getRid, id));

        List<MenuRole> list = Arrays.stream(ids)
                .map(i -> MenuRole.builder().rid(id).mid(i).build())
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            return true;
        }

        return saveBatch(list);
    }
}
