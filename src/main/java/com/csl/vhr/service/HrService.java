package com.csl.vhr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.vhr.entity.Hr;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
public interface HrService extends IService<Hr>, UserDetailsService {

}
