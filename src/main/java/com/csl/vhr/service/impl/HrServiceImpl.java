package com.csl.vhr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.vhr.entity.Hr;
import com.csl.vhr.mapper.HrMapper;
import com.csl.vhr.service.HrService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

}
