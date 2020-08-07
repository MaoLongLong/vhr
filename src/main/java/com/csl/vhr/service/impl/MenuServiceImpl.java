package com.csl.vhr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.vhr.entity.Menu;
import com.csl.vhr.mapper.MenuMapper;
import com.csl.vhr.service.MenuService;
import lombok.AllArgsConstructor;
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
    public List<Menu> getMenusByHrId(Integer id) {
        return menuMapper.selectMenusByHrId(id);
    }

}
