package com.csl.vhr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.vhr.entity.Department;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MaoLongLong
 * @since 2020-08-05
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartmentByParentId(Integer id);

}
