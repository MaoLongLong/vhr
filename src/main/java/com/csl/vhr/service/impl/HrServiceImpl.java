package com.csl.vhr.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.vhr.entity.Hr;
import com.csl.vhr.entity.HrRole;
import com.csl.vhr.mapper.HrMapper;
import com.csl.vhr.mapper.HrRoleMapper;
import com.csl.vhr.service.HrRoleService;
import com.csl.vhr.service.HrService;
import com.csl.vhr.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements HrService {

    private final HrMapper hrMapper;
    private final HrRoleMapper hrRoleMapper;
    private final HrRoleService hrRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        Hr hr = hrMapper.getHrByUsernameWithRole(username);

        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return hr;
    }

    @Override
    public List<Hr> getAll() {
        return hrMapper.selectAllWithRole(SecurityUtils.currentUser().getId());
    }

    @Override
    @Transactional
    public boolean updateHrRoles(Integer id, Integer[] roleIds) {
        hrRoleMapper.delete(Wrappers.<HrRole>lambdaUpdate().eq(HrRole::getHrid, id));

        List<HrRole> hrRoleList = Arrays.stream(roleIds)
                .map(rid -> HrRole.builder().hrid(id).rid(rid).build())
                .collect(toList());

        if (hrRoleList.isEmpty()) {
            return true;
        }

        return hrRoleService.saveBatch(hrRoleList);
    }
}
