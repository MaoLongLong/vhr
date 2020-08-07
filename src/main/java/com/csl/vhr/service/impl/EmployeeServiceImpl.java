package com.csl.vhr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.vhr.entity.Employee;
import com.csl.vhr.mapper.EmployeeMapper;
import com.csl.vhr.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
